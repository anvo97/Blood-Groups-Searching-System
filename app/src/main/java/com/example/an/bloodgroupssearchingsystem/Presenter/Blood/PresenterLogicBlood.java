package com.example.an.bloodgroupssearchingsystem.Presenter.Blood;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.an.bloodgroupssearchingsystem.Model.Blood.Events;
import com.example.an.bloodgroupssearchingsystem.Model.Blood.LoadEvensLister;
import com.example.an.bloodgroupssearchingsystem.Model.Blood.ModelBlood;
import com.example.an.bloodgroupssearchingsystem.View.Blood.ViewBlood;

import java.util.ArrayList;

public class PresenterLogicBlood implements PresenterImpBlood, LoadEvensLister {
    private ModelBlood modelBlood;
    private ViewBlood viewBlood;
    public PresenterLogicBlood(ViewBlood viewBlood){
        this.viewBlood=viewBlood;
        modelBlood=new ModelBlood(this);
    }
    @Override
    public void LoadData() {
        modelBlood.getDataEvents();
    }

    @Override
    public void InnitViewEvent(RecyclerView recyclerView, ArrayList<Events> arrayEventList) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        EventsAdapter eventsAdapter=new EventsAdapter(arrayEventList,recyclerView.getContext());
        recyclerView.setAdapter(eventsAdapter);
    }

    @Override
    public void onLoadEventsSuccess(ArrayList<Events> listNews) {
        viewBlood.DisplayNews(listNews);
    }
}
