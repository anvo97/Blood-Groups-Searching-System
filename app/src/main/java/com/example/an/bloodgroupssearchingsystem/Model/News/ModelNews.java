package com.example.an.bloodgroupssearchingsystem.Model.News;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.an.bloodgroupssearchingsystem.Presenter.News.NewsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModelNews {
    private LoadNewsLister loadNewsLister;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    final ArrayList<News> listNews=new ArrayList<>();
    public ModelNews(LoadNewsLister loadNewsLister){
        this.loadNewsLister=loadNewsLister;
    }
    public void getDataNews(){
        listNews.clear();
        mDatabase.child("News").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
                for (DataSnapshot chile:nodeChild){
                    News news=chile.getValue(News.class);
                    news.setId(chile.getKey());
                    listNews.add(news);
                }
                Log.d("AAA",listNews.get(0).getTitle());
                loadNewsLister.onLoadNewsSuccess(listNews);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void getDataDetailNews(String x){
        final ArrayList<Detail> listDetailNews=new ArrayList<>();
        mDatabase.child("News").child(x).child("Detail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
                for (DataSnapshot chile:nodeChild){
                Detail news=chile.getValue(Detail.class);
                    listDetailNews.add(news);
                }
                loadNewsLister.onLoadDetailNewsSuccess(listDetailNews);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
