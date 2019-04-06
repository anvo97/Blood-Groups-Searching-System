package com.example.an.bloodgroupssearchingsystem.Presenter.Blood;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.Blood.Events;
import com.example.an.bloodgroupssearchingsystem.R;

import java.util.ArrayList;

class ViewHolderEvents extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txtTitleEvens,txtextraContent,txtTimePost;
    public ViewHolderEvents(@NonNull View itemView) {
        super(itemView);
        txtTitleEvens=(TextView)itemView.findViewById(R.id.txtTitleEvens);
        txtextraContent=(TextView)itemView.findViewById(R.id.txtextraContent);
        txtTimePost=(TextView)itemView.findViewById(R.id.txtTimePost);
    }

    @Override
    public void onClick(View v) {

    }
}

public class EventsAdapter extends RecyclerView.Adapter<ViewHolderEvents> {
    private ArrayList<Events> arrayEventList;
    private Context context;

    public EventsAdapter(ArrayList<Events> arrayEventList, Context context) {
        this.arrayEventList = arrayEventList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderEvents onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View itemView=inflater.inflate(R.layout.item_row_recyclerview_evens,viewGroup,false);
        return new ViewHolderEvents(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEvents viewHolderEvents, int i) {
        viewHolderEvents.txtTitleEvens.setText(arrayEventList.get(i).getTitle());
        viewHolderEvents.txtTimePost.setText(arrayEventList.get(i).getTimePost());
        viewHolderEvents.txtextraContent.setText(arrayEventList.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return arrayEventList.size();
    }
}
