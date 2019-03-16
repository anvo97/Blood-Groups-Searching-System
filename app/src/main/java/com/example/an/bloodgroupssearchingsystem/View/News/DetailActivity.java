package com.example.an.bloodgroupssearchingsystem.View.News;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Model.News.Detail;
import com.example.an.bloodgroupssearchingsystem.Presenter.News.PresenterLogicNews;
import com.example.an.bloodgroupssearchingsystem.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements ViewDetailNews {
    private RecyclerView recyclerView;
    PresenterLogicNews presenterLogicNews=new PresenterLogicNews(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView=(RecyclerView)findViewById(R.id.lvDetailnews) ;
        Intent intent=getIntent();
        String id=intent.getStringExtra("IDItemList");
        Toast.makeText(this, ""+id ,Toast.LENGTH_SHORT).show();
        presenterLogicNews.LoadDataDetail();
    }

    @Override
    public void DisplayDetailNews(ArrayList<Detail> listDetailNews) {
        presenterLogicNews.InnitViewDetail(recyclerView,listDetailNews);
    }
}
