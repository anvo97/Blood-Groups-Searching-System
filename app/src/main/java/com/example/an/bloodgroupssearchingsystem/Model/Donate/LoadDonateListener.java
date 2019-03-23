package com.example.an.bloodgroupssearchingsystem.Model.Donate;

import com.example.an.bloodgroupssearchingsystem.Model.Search.ListSearch;

import java.util.List;

public interface LoadDonateListener {
    void onLoadDonateSuccess(List<String> ListDonate);
    void onLoadDemoFailure(String message);
}
