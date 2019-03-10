package com.example.an.bloodgroupssearchingsystem.Presenter.News;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView txtTitleItem,txtTimeItem;
    RoundedImageView imgItemView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitleItem=(TextView)itemView.findViewById(R.id.txtTitleItem);
        txtTimeItem=(TextView)itemView.findViewById(R.id.txtTimeItem);
        imgItemView=(RoundedImageView)itemView.findViewById(R.id.imgItemView);
    }

    @Override
    public void onClick(View v) {

    }
}
public class NewsAdapter extends RecyclerView.Adapter<ViewHolder>{
    private ArrayList<News> listNews;
    private Context context;

    public NewsAdapter(ArrayList<News> listNews, Context context) {
        this.listNews = listNews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.item_row_recyclerview,viewGroup,false);
        return new ViewHolder(itemView);
    }


    //gan du lieu
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtTitleItem.setText(listNews.get(i).getTitle());
        viewHolder.txtTimeItem.setText(listNews.get(i).getTime());
        Picasso.get().load(listNews.get(i).getPicture()).into(viewHolder.imgItemView);


    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }
}
