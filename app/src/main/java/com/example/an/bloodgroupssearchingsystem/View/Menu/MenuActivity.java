package com.example.an.bloodgroupssearchingsystem.View.Menu;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.an.bloodgroupssearchingsystem.Model.Blood.Events;
import com.example.an.bloodgroupssearchingsystem.Notificaiton.EvensNotificationHelper;
import com.example.an.bloodgroupssearchingsystem.Notificaiton.NotificationHelper;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.Service.EvensService;
import com.example.an.bloodgroupssearchingsystem.View.Blood.BloodFragment;
import com.example.an.bloodgroupssearchingsystem.View.Donate.DonateFragment;
import com.example.an.bloodgroupssearchingsystem.View.Donate.Event;
import com.example.an.bloodgroupssearchingsystem.View.Login.LoginActivity;
import com.example.an.bloodgroupssearchingsystem.View.News.NewsFragment;
import com.example.an.bloodgroupssearchingsystem.View.Search.SearchFragment;
import com.example.an.bloodgroupssearchingsystem.View.UpdateInformation.UserFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private SearchFragment searchFragment;
    private NewsFragment newsFragment;
    private BloodFragment bloodFragment;
    private UserFragment userFragment;
    private DonateFragment donateFragment;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = new Intent(this, EvensService.class);
        startService(intent);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);

        searchFragment = new SearchFragment();
        newsFragment = new NewsFragment();
        bloodFragment = new BloodFragment();
        userFragment = new UserFragment();
        donateFragment = new DonateFragment();


        setFratment(searchFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.nav_search:
                        setFratment(searchFragment);
                        return true;
                    case R.id.nav_news:
                        setFratment(newsFragment);
                        return true;
                    case R.id.nav_blood:
                        setFratment(bloodFragment);
                        return true;
                    case R.id.nav_donate:
                        setFratment(donateFragment);
                        return true;
                    case R.id.nav_user:
                        setFratment(userFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

    }

    private void setFratment(Fragment fratment) {

        @SuppressLint("CommitTransaction") FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fratment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
