package com.example.an.bloodgroupssearchingsystem.Model.News;

import java.util.ArrayList;

public interface LoadNewsLister {
    void onLoadNewsSuccess(ArrayList<News> listNews);
    void onLoadDetailNewsSuccess(ArrayList<Detail>listDetailNews);
}
