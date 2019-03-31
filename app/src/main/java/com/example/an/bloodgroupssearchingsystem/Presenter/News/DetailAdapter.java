package com.example.an.bloodgroupssearchingsystem.Presenter.News;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.News.Detail;
import com.example.an.bloodgroupssearchingsystem.Model.News.News;
import com.example.an.bloodgroupssearchingsystem.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ViewHolderDetail extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txtContext,txtTitleImage;
    ImageView imgDetail;
    public ViewHolderDetail(@NonNull View itemView) {
        super(itemView);
        txtContext=(TextView)itemView.findViewById(R.id.txtContent);
        txtTitleImage=(TextView)itemView.findViewById(R.id.txtTitleImage);
        imgDetail=(ImageView)itemView.findViewById(R.id.imgDetail);
    }

    @Override
    public void onClick(View v) {

    }
}
public class DetailAdapter extends RecyclerView.Adapter<ViewHolderDetail> {
    private ArrayList<Detail> listDetilNews;
    private Context context;

    public DetailAdapter(ArrayList<Detail> listDetilNews, Context context) {
        this.listDetilNews = listDetilNews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDetail onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.item_row_recyclerview_detailnews,viewGroup,false);
        return new ViewHolderDetail(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDetail viewHolderDetail, int i) {
        viewHolderDetail.txtContext.setText(listDetilNews.get(i).getContent());
        viewHolderDetail.txtTitleImage.setText(listDetilNews.get(i).getTitleImage());
        Picasso.get().load(listDetilNews.get(i).getImageDetail()).into(viewHolderDetail.imgDetail);
    }

    @Override
    public int getItemCount() {
        return listDetilNews.size();
    }
}
