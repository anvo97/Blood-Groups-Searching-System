package com.example.an.bloodgroupssearchingsystem.Presenter.News;

import android.support.v7.widget.RecyclerView;

import com.example.an.bloodgroupssearchingsystem.Model.News.News;

import java.util.ArrayList;

public interface PresenterImpNews {
    void InnitView(RecyclerView recyclerView, ArrayList<News> listNews);
    void LoadData();
}
