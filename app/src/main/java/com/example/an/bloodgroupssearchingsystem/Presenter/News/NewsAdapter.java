package com.example.an.bloodgroupssearchingsystem.Presenter.News;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.News.DetailActivity;
import com.example.an.bloodgroupssearchingsystem.View.News.ItemClickListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ViewHolderNews extends RecyclerView.ViewHolder implements View.OnClickListener{
    ItemClickListener itemClickListener;
    TextView txtTitleItem,txtTimeItem;
    WebView webView;
    RoundedImageView imgItemView;
    public ViewHolderNews(@NonNull View itemView) {
        super(itemView);
        txtTitleItem=(TextView)itemView.findViewById(R.id.txtTitleItem);
        txtTimeItem=(TextView)itemView.findViewById(R.id.txtTimeItem);
        imgItemView=(RoundedImageView)itemView.findViewById(R.id.imgItemView);
        webView=itemView.findViewById(R.id.webView);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
public class NewsAdapter extends RecyclerView.Adapter<ViewHolderNews>{
    private ArrayList<News> listNews;
    private Context context;

    public NewsAdapter(ArrayList<News> listNews, Context context) {
        this.listNews = listNews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderNews onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.item_row_recyclerview,viewGroup,false);
        return new ViewHolderNews(itemView);
    }


    //gan du lieu
    @Override
    public void onBindViewHolder(@NonNull ViewHolderNews viewHolder, final int i) {
        viewHolder.txtTitleItem.setText(listNews.get(i).getTitle());
        viewHolder.txtTimeItem.setText(listNews.get(i).getTime());
        Picasso.get().load(listNews.get(i).getImage()).into(viewHolder.imgItemView);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean islongClick) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("IDItemList",listNews.get(position).getId());
                intent.putExtra("Titlenews",listNews.get(position).getTitle());
                intent.putExtra("Time",listNews.get(position).getTime());
                intent.putExtra("localhost",listNews.get(position).getLocalhost());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }
}
