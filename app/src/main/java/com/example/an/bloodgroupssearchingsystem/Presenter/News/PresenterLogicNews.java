package com.example.an.bloodgroupssearchingsystem.Presenter.News;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.an.bloodgroupssearchingsystem.Model.News.Detail;
import com.example.an.bloodgroupssearchingsystem.Model.News.LoadNewsLister;
import com.example.an.bloodgroupssearchingsystem.Model.News.ModelNews;
import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.News.ViewDetailNews;
import com.example.an.bloodgroupssearchingsystem.View.News.ViewNews;

import java.util.ArrayList;

public class PresenterLogicNews implements PresenterImpNews, LoadNewsLister {
    private ModelNews modelNews;
    private ViewNews viewNews;
    private ViewDetailNews viewDetailNews;

    public PresenterLogicNews(ViewDetailNews viewDetailNews) {
        this.viewDetailNews = viewDetailNews;
        modelNews=new ModelNews(this);
    }

    public PresenterLogicNews(ViewNews viewNews){
        this.viewNews=viewNews;
        modelNews=new ModelNews(this);
    }
    @Override
    public void onLoadNewsSuccess(ArrayList<News> listNews) {
        viewNews.DisplayNews(listNews);
    }

    @Override
    public void onLoadDetailNewsSuccess(ArrayList<Detail> listDetailNews) {
        viewDetailNews.DisplayDetailNews(listDetailNews);
    }

    @Override
    public void InnitView(RecyclerView recyclerView, ArrayList<News> listNews) {
        recyclerView.setHasFixedSize(true);
        listNews.remove(0);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter newsAdapter=new NewsAdapter(listNews,recyclerView.getContext());
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void LoadData() {
        modelNews.getDataNews();
    }

    @Override
    public void InnitViewDetail(RecyclerView recyclerView, ArrayList<Detail> listDetailNews) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DetailAdapter detailAdapter=new DetailAdapter(listDetailNews,recyclerView.getContext());
        recyclerView.setAdapter(detailAdapter);
    }

    @Override
    public void LoadDataDetail() {
        modelNews.getDataDetailNews();
    }
}
