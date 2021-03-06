package com.example.an.bloodgroupssearchingsystem.Presenter.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.Search.ListSearch;
import com.example.an.bloodgroupssearchingsystem.R;

import java.util.List;

public class ListSearchAdapter extends BaseAdapter {

    Context context;
    private int mLayOut;
    private List<ListSearch> arrayListSearch;

    public ListSearchAdapter(Context context, int mLayOut, List<ListSearch> arrayListSearch) {
        this.context = context;
        this.mLayOut = mLayOut;
        this.arrayListSearch = arrayListSearch;
    }

    @Override
    public int getCount() {
        return arrayListSearch.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView txtPhone, txtNameHospital;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mLayOut,null);
            viewHolder = new ViewHolder();

            viewHolder.txtPhone = (TextView)convertView.findViewById(R.id.phoneCus);
            viewHolder.txtNameHospital = (TextView)convertView.findViewById(R.id.txtNameHos);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ListSearch listSearch = arrayListSearch.get(position);

        viewHolder.txtPhone.setText(listSearch.getPhone());
        viewHolder.txtNameHospital.setText(listSearch.getName());

        return convertView;
    }
}
