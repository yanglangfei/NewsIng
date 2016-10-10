package com.iknown.ylf.iknown.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iknown.ylf.iknown.R;
import com.iknown.ylf.iknown.model.History;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */

public class HistoryAdapter extends BaseAdapter {
    private final List<History> histories;
    private final Context context;

    public HistoryAdapter(List<History> histories, Context context) {
        this.histories=histories;
        this.context=context;
    }

    @Override
    public int getCount() {
        return histories.size();
    }

    @Override
    public Object getItem(int i) {
        return histories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.ui_hostory_item,null);
        }
        ImageView historyImg= (ImageView) view.findViewById(R.id.historyImg);
        TextView historyTitle= (TextView) view.findViewById(R.id.historyTitle);
        TextView historyDesc= (TextView) view.findViewById(R.id.historyDesc);
        Glide.with(context).load(histories.get(i).getPic())
                .placeholder(R.drawable.logo_no).into(historyImg);
        historyTitle.setText(histories.get(i).getTitle());
        historyDesc.setText(histories.get(i).getDes());
        return view;
    }
}
