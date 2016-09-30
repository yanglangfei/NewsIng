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
import com.iknown.ylf.iknown.model.Article;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {
	private Context context;
	private List<Article> articles;

	public ArticleAdapter(List<Article> articles, Context context) {
		this.context = context;
		this.articles = articles;
	}

	public void resumeLoad(){
		Glide.with(context).resumeRequests();
	}

	public  void pauseLoad(){
		Glide.with(context).pauseRequests();
	}

	public void setNewsArray(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.ui_article_item, null);
		}

		ImageView iv_article_logo = (ImageView) convertView
				.findViewById(R.id.iv_article_logo);
		TextView tv_article_title = (TextView) convertView
				.findViewById(R.id.tv_article_title);
		TextView tv_source = (TextView) convertView
				.findViewById(R.id.tv_source);
		tv_article_title.setText(articles.get(position).getTitle());
		tv_source.setText(articles.get(position).getSource());
		Glide.with(context).load(articles.get(position).getFirstImage())
				.into(iv_article_logo);

		return convertView;
	}

}
