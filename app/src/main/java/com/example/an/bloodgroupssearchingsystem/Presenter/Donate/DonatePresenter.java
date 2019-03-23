package com.example.an.bloodgroupssearchingsystem.Presenter.Donate;

import com.example.an.bloodgroupssearchingsystem.Model.Donate.DonateInterator;
import com.example.an.bloodgroupssearchingsystem.Model.Donate.LoadDonateListener;
import com.example.an.bloodgroupssearchingsystem.View.Donate.DonateView;

import java.util.List;

public class DonatePresenter implements LoadDonateListener {

    private DonateInterator donateInterator;
    private DonateView donateView;

    public DonatePresenter(DonateInterator donateInterator, DonateView donateView) {
        this.donateInterator = donateInterator;
        this.donateView = donateView;
    }

    @Override
    public void onLoadDonateSuccess(List<String> ListDonate) {

    }

    @Override
    public void onLoadDemoFailure(String message) {

    }
}
