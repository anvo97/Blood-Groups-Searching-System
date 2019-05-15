package com.example.an.bloodgroupssearchingsystem.Service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Model.Blood.Events;
import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.Notificaiton.EvensNotificationHelper;
import com.example.an.bloodgroupssearchingsystem.Notificaiton.NotificationHelper;
import com.example.an.bloodgroupssearchingsystem.View.Menu.MenuActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class EvensService extends Service {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    EvensNotificationHelper evensNotificationHelper;
    String status;
    @Nullable
    @android.support.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        evensNotificationHelper=new EvensNotificationHelper(getApplicationContext());
        Log.d("AAA","onCreate");
        mDatabase.child("Event-text").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                Log.d("AAA","co tin moi");
                if (s!=null){
                    status=dataSnapshot.child("Status").getValue().toString();
                    if (status.equals("false"))
                    {
                        String title = dataSnapshot.child("Title").getValue().toString();
                        String content = dataSnapshot.child("content").getValue().toString().substring(0,69)+"...";
                        evensNotificationHelper.ActionNotification(title,content);
                        mDatabase.child("Event-text").child(dataSnapshot.getKey()).child("Status").setValue("true");
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
