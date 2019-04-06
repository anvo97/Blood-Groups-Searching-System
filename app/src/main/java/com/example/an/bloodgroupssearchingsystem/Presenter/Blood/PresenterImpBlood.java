package com.example.an.bloodgroupssearchingsystem.Presenter.Blood;

import android.support.v7.widget.RecyclerView;

import com.example.an.bloodgroupssearchingsystem.Model.Blood.Events;

import java.util.ArrayList;

public interface PresenterImpBlood {
    void LoadData();
    void InnitViewEvent(RecyclerView recyclerView, ArrayList<Events> arrayEventList);
}
