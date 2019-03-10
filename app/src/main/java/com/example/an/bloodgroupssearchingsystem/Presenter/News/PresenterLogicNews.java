package com.example.an.bloodgroupssearchingsystem.Presenter.News;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.an.bloodgroupssearchingsystem.Model.News.LoadNewsLister;
import com.example.an.bloodgroupssearchingsystem.Model.News.ModelNews;
import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.News.ViewNews;

import java.util.ArrayList;

public class PresenterLogicNews implements PresenterImpNews, LoadNewsLister {
    private ModelNews modelNews;
    private ViewNews viewNews;
    public PresenterLogicNews(ViewNews viewNews){
        this.viewNews=viewNews;
        modelNews=new ModelNews(this);
    }
    @Override
    public void onLoadNewsSuccess(ArrayList<News> listNews) {
        viewNews.DisplayNews(listNews);
    }

    @Override
    public void InnitView(RecyclerView recyclerView, ArrayList<News> listNews) {
        recyclerView.setHasFixedSize(true);
        listNews.remove(0);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        //Drawable drawable= ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.custom_divider);
        //dividerItemDecoration.setDrawable(drawable);
        //recyclerView.addItemDecoration(dividerItemDecoration);
        NewsAdapter newsAdapter=new NewsAdapter(listNews,recyclerView.getContext());
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void LoadData() {
        modelNews.getDataNews();
    }
}
