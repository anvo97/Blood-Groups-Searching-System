package com.example.an.bloodgroupssearchingsystem.View.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Menu.MenuActivity;
import com.example.an.bloodgroupssearchingsystem.View.QRCodeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e)
                {

                }finally {
                    Intent intent=new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        thread.start();
    }
}
