package com.example.an.bloodgroupssearchingsystem.View.Menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Blood.BloodFragment;
import com.example.an.bloodgroupssearchingsystem.View.Donate.DonateFragment;
import com.example.an.bloodgroupssearchingsystem.View.News.NewsFragment;
import com.example.an.bloodgroupssearchingsystem.View.Search.SearchFragment;
import com.example.an.bloodgroupssearchingsystem.View.User.UserFragment;

public class MenuActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private SearchFragment searchFragment;
    private NewsFragment newsFragment;
    private BloodFragment bloodFragment;
    private UserFragment userFragment;
    private DonateFragment donateFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mMainNav = (BottomNavigationView)findViewById(R.id.main_nav);
        mMainFrame = (FrameLayout)findViewById(R.id.main_frame);

        searchFragment = new SearchFragment();
        newsFragment = new NewsFragment();
        bloodFragment = new BloodFragment();
        userFragment = new UserFragment();
        donateFragment = new DonateFragment();


        setFratment(searchFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.nav_search :
                        setFratment(searchFragment);
                        return true;
                    case R.id.nav_news :
                        setFratment(newsFragment);
                        return true;
                    case R.id.nav_blood :
                        setFratment(bloodFragment);
                        return true;
                    case R.id.nav_donate :
                        setFratment(donateFragment);
                        return true;
                    case R.id.nav_user :
                        setFratment(userFragment);
                        return true;

                        default:
                            return false;
                }

            }
        });

    }
    private void setFratment(Fragment fratment){

        @SuppressLint("CommitTransaction") FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fratment);
        fragmentTransaction.commit();
    }
}
