package com.example.an.bloodgroupssearchingsystem.View.Blood;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.an.bloodgroupssearchingsystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodFragment extends Fragment {


    public BloodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blood, container, false);


        return view;
    }

}
