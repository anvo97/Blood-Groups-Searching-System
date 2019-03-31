package com.example.an.bloodgroupssearchingsystem.View.News;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.News.Detail;
import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.Presenter.News.PresenterLogicNews;
import com.example.an.bloodgroupssearchingsystem.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.shapeofview.shapes.DiagonalView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements ViewNews {
    KenBurnsView kenBurnsView;
    DiagonalView diagonalView;
    TextView top_title;

    PresenterLogicNews presenterLogicNews=new PresenterLogicNews(this);
    RecyclerView recyclerView;

    private String id;
    private String TitleNews;
    private String Time;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.list_news);
        kenBurnsView=(KenBurnsView)view.findViewById(R.id.top_image);
        diagonalView=(DiagonalView)view.findViewById(R.id.diagonallayout);
        top_title=(TextView)view.findViewById(R.id.txtTopTitle);

        diagonalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),DetailActivity.class);
                intent.putExtra("IDItemList",id);
                intent.putExtra("Titlenews",TitleNews);
                intent.putExtra("Time",Time);
                startActivity(intent);
            }
        });
        presenterLogicNews.LoadData();
        return view;
    }

    @Override
    public void DisplayNews(ArrayList<News> listnNews) {
                top_title.setText(listnNews.get(0).getTitle());
                id=listnNews.get(0).getId();
                TitleNews=listnNews.get(0).getTitle();
                Time=listnNews.get(0).getTime();
                Picasso.get().load(listnNews.get(0).getImage()).into(kenBurnsView);
                presenterLogicNews.InnitView(recyclerView,listnNews);
    }
}
