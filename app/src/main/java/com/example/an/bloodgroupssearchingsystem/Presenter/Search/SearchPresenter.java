package com.example.an.bloodgroupssearchingsystem.Presenter.Search;

import com.example.an.bloodgroupssearchingsystem.Model.Search.ListSearch;
import com.example.an.bloodgroupssearchingsystem.Model.Search.LoadSearchListener;
import com.example.an.bloodgroupssearchingsystem.Model.Search.LoadSearchListener;
import com.example.an.bloodgroupssearchingsystem.Model.Search.SearchInterator;
import com.example.an.bloodgroupssearchingsystem.Model.Search.SearchInterator;
import com.example.an.bloodgroupssearchingsystem.View.Search.SearchView;
import com.example.an.bloodgroupssearchingsystem.View.Search.SearchView;

import java.util.List;

public class SearchPresenter implements LoadSearchListener {


    private SearchInterator searchInterator;
    private SearchView searchView;

    public SearchPresenter(SearchView searchView) {
        this.searchView = searchView;
        searchInterator = new SearchInterator(this);
    }
    public void LoadSearchBlood(){
        searchInterator.taodulieu();
    }

    public void LoadSearchCounty(){
        searchInterator.taoQuan();
    }

    public void LoadListSearch(){
        searchInterator.getDataSearch();
    }

    @Override
    public void onLoadSearchSuccess(List<String> ListSearch) {
        searchView.displaySearchBlood(ListSearch);
    }

    public void onLoadSearchSuccess2(List<String> ListSearch){
        searchView.displaySearchCounty(ListSearch);
    }

    public void onLoadSearchSuccess3(List<ListSearch> ListSearch){
        searchView.displayListSearch(ListSearch);
    }

    @Override
    public void onLoadDemoFailure(String message) {

    }
}
