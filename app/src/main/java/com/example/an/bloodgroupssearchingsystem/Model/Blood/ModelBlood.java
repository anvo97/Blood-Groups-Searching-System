package com.example.an.bloodgroupssearchingsystem.Model.Blood;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModelBlood {
    private LoadEvensLister loadEvensLister;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private ArrayList<Events> arrayEventList=new ArrayList<>();
    public ModelBlood(LoadEvensLister loadEvensLister){
        this.loadEvensLister=loadEvensLister;
    }
    public void getDataEvents(){
        mDatabase.child("Event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayEventList.clear();
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
                for (DataSnapshot chile:nodeChild){
                    Events events=chile.getValue(Events.class);
                    events.setContent(chile.child("Detail").child("content").getValue().toString());
                    events.setId(chile.getKey());
                    arrayEventList.add(events);
                }
                loadEvensLister.onLoadEventsSuccess(arrayEventList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
