package com.example.an.bloodgroupssearchingsystem.Model.Search;

import android.support.annotation.NonNull;
import android.widget.ListView;

import com.example.an.bloodgroupssearchingsystem.View.Donate.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchInterator {

    private LoadSearchListener LoadSearchListener;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();


    public SearchInterator(LoadSearchListener LoadSearchListener) {
        this.LoadSearchListener = LoadSearchListener;
    }

    public void taodulieu() {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(new String("O"));
        arrayList.add(new String("A"));
        arrayList.add(new String("B"));
        arrayList.add(new String("AB"));

        LoadSearchListener.onLoadSearchSuccess(arrayList);
    }

    public void taoQuan() {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(new String(" HẢI CHÂU"));
        arrayList.add(new String(" CẨM LỆ"));
        arrayList.add(new String(" THANH KHÊ"));
        arrayList.add(new String(" LIÊN CHIỂU"));
        arrayList.add(new String(" NGŨ HÀNH SƠN"));
        arrayList.add(new String(" SƠN TRÀ"));
        arrayList.add(new String(" HÒA VANG"));
        arrayList.add(new String(" HOÀNG SA"));


        LoadSearchListener.onLoadSearchSuccess2(arrayList);
    }

}
