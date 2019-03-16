package com.example.an.bloodgroupssearchingsystem.Model.Search;

import java.util.List;

public interface LoadSearchListener {
    void onLoadSearchSuccess(List<String> ListSearch);
    void onLoadSearchSuccess2(List<String> ListSearch);
    void onLoadSearchSuccess3(List<ListSearch> ListSearch);
    void onLoadDemoFailure(String message);
}
