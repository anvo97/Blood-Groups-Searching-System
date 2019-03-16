package com.example.an.bloodgroupssearchingsystem.Model.Search;

import java.util.ArrayList;

public class SearchInterator {

    private LoadSearchListener LoadSearchListener;

    public SearchInterator(LoadSearchListener  LoadSearchListener) {
        this.LoadSearchListener=LoadSearchListener;
    }

    public  void taodulieu(){

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(new String("O"));
        arrayList.add(new String("A"));
        arrayList.add(new String("B"));
        arrayList.add(new String("AB"));

        LoadSearchListener.onLoadSearchSuccess(arrayList);
    }

    public void taoQuan(){

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(new String("Hải Châu"));
        arrayList.add(new String("Cẩm Lệ"));
        arrayList.add(new String("Thanh Khê"));
        arrayList.add(new String("Liên Chiểu"));
        arrayList.add(new String("Ngũ Hành Sơn"));
        arrayList.add(new String("Sơn Trà"));
        arrayList.add(new String("Hòa Vang"));
        arrayList.add(new String("Hoàng Sa"));


        LoadSearchListener.onLoadSearchSuccess2(arrayList);
    }

    public void getDataSearch(){
        ArrayList<ListSearch> arrayList = new ArrayList<ListSearch>();

        arrayList.add(new ListSearch("08988741427","benh vien gia dinh",""));
        arrayList.add(new ListSearch("08988723557","benh vien hoan my",""));

        LoadSearchListener.onLoadSearchSuccess3(arrayList);
    }

}
