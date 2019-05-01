package com.example.an.bloodgroupssearchingsystem.View.Blood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Donate.DonateFragment;

public class DetailEventActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_register_event;
    private TextView text_Title_name_event, text_Time_Event, text_place_Event, text_content_event, text_name_events;
    private String mTitle, mPlace, mTime, mContent, mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        innitView();
        Intent i = getIntent();
        mTitle = i.getStringExtra("title");
        mPlace = i.getStringExtra("place");
        mContent = i.getStringExtra("content");
        mTime = i.getStringExtra("time");
        mName = i.getStringExtra("name");
        text_Title_name_event.setText(mTitle);
        text_content_event.setText(mContent);
        text_place_Event.setText(mPlace);
        text_Time_Event.setText(mTime);
        text_name_events.setText(mName);
        button_register_event.setOnClickListener(this);
    }

    private void innitView() {
        button_register_event = findViewById(R.id.button_register_event);
        text_Title_name_event = findViewById(R.id.text_Title_name_event);
        text_Time_Event = findViewById(R.id.text_Time_Event);
        text_place_Event = findViewById(R.id.text_place_Event);
        text_content_event = findViewById(R.id.text_content_event);
        text_name_events = findViewById(R.id.text_name_events);
    }

    @Override
    public void onClick(View v) {
        if (v == button_register_event) {

        }
    }
}
