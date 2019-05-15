package com.example.an.bloodgroupssearchingsystem.View.News;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.example.an.bloodgroupssearchingsystem.Model.News.Detail;
import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.Presenter.News.PresenterLogicNews;
import com.example.an.bloodgroupssearchingsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class DetailActivity extends AppCompatActivity implements ViewDetailNews {
    private RecyclerView recyclerView;
    private WebView mWebView;
    private SVProgressHUD mSvProgressHUD;
    String localhost;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    PresenterLogicNews presenterLogicNews=new PresenterLogicNews(this);
    String id;
    private TextView txtTitleDetail,txtTimeDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mSvProgressHUD=new SVProgressHUD(this);
        mSvProgressHUD.show();
        recyclerView=(RecyclerView)findViewById(R.id.lvDetailnews) ;
        txtTitleDetail=(TextView)findViewById(R.id.txtTitleDetail);
        txtTimeDetail=(TextView)findViewById(R.id.txtTimeDetail);
        mWebView=findViewById(R.id.webView);
        Intent intent=getIntent();
        id=intent.getStringExtra("IDItemList");
        String TitleNews=intent.getStringExtra("Titlenews");
        String Time=intent.getStringExtra("Time");
        localhost=intent.getStringExtra("localhost");
        presenterLogicNews.LoadDataDetail(id);
        mDatabase.child("News").child(id).child("localhost").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                localhost=dataSnapshot.getValue().toString();
                mWebView.setWebViewClient(new WebViewClient());
                mWebView.loadUrl(localhost);
                SystemClock.sleep(2000);
                mSvProgressHUD.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void DisplayDetailNews(ArrayList<Detail> listDetailNews) {
        presenterLogicNews.InnitViewDetail(recyclerView,listDetailNews);
    }

}
