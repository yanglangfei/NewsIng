package com.iknown.ylf.iknown.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iknown.ylf.iknown.R;
import com.iknown.ylf.iknown.model.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<News> newsArray;

    public NewsAdapter(List<News> newsArray, Context context) {
        this.context = context;
        this.newsArray = newsArray;
    }

    public void resumeLoad() {
        Glide.with(context).resumeRequests();
    }

    public void pauseLoad() {
        Glide.with(context).pauseRequests();
    }

    public void setNewsArray(List<News> newsArray) {
        this.newsArray = newsArray;
    }

    @Override
    public int getCount() {
        return newsArray.size();
    }

    @Override
    public Object getItem(int position) {
        return newsArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.ui_news_item, null);
        }

        ImageView iv_news_logo = (ImageView) convertView
                .findViewById(R.id.iv_news_logo);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        Glide.with(context).load(newsArray.get(position).getThumbnail_pic_s())
                .into(iv_news_logo);
        tv_title.setText(newsArray.get(position).getTitle());
        tv_time.setText(newsArray.get(position).getDate());
        return convertView;
    }

}
