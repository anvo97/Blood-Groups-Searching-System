package com.example.an.bloodgroupssearchingsystem.View.Blood;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.example.an.bloodgroupssearchingsystem.Model.Blood.Events;
import com.example.an.bloodgroupssearchingsystem.Presenter.Blood.PresenterLogicBlood;
import com.example.an.bloodgroupssearchingsystem.R;

import java.util.ArrayList;

/**p
 * A simple {@link Fragment} subclass.
 */
public class BloodFragment extends Fragment implements ViewBlood {
    private RecyclerView recyclerView;
    private PresenterLogicBlood presenterLogicBlood=new PresenterLogicBlood(this);
    private SVProgressHUD mSvProgressHUD;

    public BloodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blood, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerviewEvents);
        mSvProgressHUD=new SVProgressHUD(getContext());
        mSvProgressHUD.show();
        presenterLogicBlood.LoadData();

        return view;
    }

    @Override
    public void DisplayNews(ArrayList<Events> listnNews) {
        presenterLogicBlood.InnitViewEvent(recyclerView,listnNews);
        mSvProgressHUD.dismiss();
    }
}
