package com.example.an.bloodgroupssearchingsystem.View.News;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    TextView top_title,top_time;

    PresenterLogicNews presenterLogicNews=new PresenterLogicNews(this);
    RecyclerView recyclerView;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.list_news);
        kenBurnsView=(KenBurnsView)view.findViewById(R.id.top_image);
        diagonalView=(DiagonalView)view.findViewById(R.id.diagonallayout);
        top_title=(TextView)view.findViewById(R.id.txtTopTitle);

        diagonalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code late
            }
        });

        presenterLogicNews.LoadData();
        return view;

    }


    @Override
    public void DisplayNews(ArrayList<News> listnNews) {
        top_title.setText(listnNews.get(0).getTitle());
        Picasso.get().load(listnNews.get(0).getPicture()).into(kenBurnsView);
        presenterLogicNews.InnitView(recyclerView,listnNews);
    }
}
