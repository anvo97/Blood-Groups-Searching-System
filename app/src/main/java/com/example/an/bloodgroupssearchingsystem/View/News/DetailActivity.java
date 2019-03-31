package com.example.an.bloodgroupssearchingsystem.View.News;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Model.News.Detail;
import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.Presenter.News.PresenterLogicNews;
import com.example.an.bloodgroupssearchingsystem.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements ViewDetailNews {
    private RecyclerView recyclerView;
    PresenterLogicNews presenterLogicNews=new PresenterLogicNews(this);
    String id;
    private TextView txtTitleDetail,txtTimeDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView=(RecyclerView)findViewById(R.id.lvDetailnews) ;
        txtTitleDetail=(TextView)findViewById(R.id.txtTitleDetail);
        txtTimeDetail=(TextView)findViewById(R.id.txtTimeDetail);
        Intent intent=getIntent();
        id=intent.getStringExtra("IDItemList");
        String TitleNews=intent.getStringExtra("Titlenews");
        String Time=intent.getStringExtra("Time");
        txtTitleDetail.setText(TitleNews);
        txtTimeDetail.setText(Time);
        presenterLogicNews.LoadDataDetail(id);

    }

    @Override
    public void DisplayDetailNews(ArrayList<Detail> listDetailNews) {
        presenterLogicNews.InnitViewDetail(recyclerView,listDetailNews);
    }

}
