package com.example.an.bloodgroupssearchingsystem.Model.News;

import java.util.ArrayList;

public class ModelNews {
    private LoadNewsLister loadNewsLister;
    public ModelNews(LoadNewsLister loadNewsLister){
        this.loadNewsLister=loadNewsLister;
    }
    public void getDataNews(){
        ArrayList<News> listNews=new ArrayList<>();
        listNews.add(new News("1 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("2 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("3 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("4 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        listNews.add(new News("5 Batman v Superman","05:56 20/01/2019","https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs_bg.jpg?alt=media&token=b2e76f2e-b130-4b91-ae50-cf48ac19402a"));
        loadNewsLister.onLoadNewsSuccess(listNews);
    }
}
