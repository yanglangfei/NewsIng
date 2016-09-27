package com.iknown.ylf.iknown.fragment;

import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import com.iknown.ylf.iknown.activity.NewsDetail;
import com.iknown.ylf.iknown.R;
import com.iknown.ylf.iknown.adapter.ArticleAdapter;
import com.iknown.ylf.iknown.model.Article;
import com.iknown.ylf.iknown.utils.Contact;
import com.iknown.ylf.iknown.utils.JsonUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArticleFragment extends Fragment implements OnScrollListener {
    private static final String GET_ARTICLE = "http://v.juhe.cn/weixin/query";
    private View view;
    private ListView lv_articel;
    private ProgressBar article_pb;
    private ArticleAdapter adapter;
    private int page = 1;
    private List<Article> articles = new ArrayList<Article>();
    private View footer;
    private TextView tv_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ui_articel_list, container, false);
        initView();
        initData(1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_title = (TextView) getActivity().findViewById(R.id.tv_title);
        tv_title.setVisibility(View.GONE);
    }

    private void initData(int page) {
        lv_articel.setVisibility(View.GONE);
        article_pb.setVisibility(View.VISIBLE);
        RequestParams param = new RequestParams(GET_ARTICLE);
        param.addParameter("ps", 20);
        param.addParameter("key", Contact.ARTICLE_KEY);
        param.addParameter("pno", page);
        param.addParameter("dtype", "json");
        x.http().get(param, new Callback.CacheCallback<String>() {

            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(String arg0) {
                lv_articel.removeFooterView(footer);
                if (arg0 != null) {
                    articles = JsonUtils.getArticleList(arg0, articles);
                }
                adapter.setNewsArray(articles);
                adapter.notifyDataSetChanged();
                article_pb.setVisibility(View.GONE);
                lv_articel.setVisibility(View.VISIBLE);

            }

            @Override
            public boolean onCache(String arg0) {
                // TODO Auto-generated method stub
                return false;
            }
        });

    }

    private void initView() {
        lv_articel = (ListView) view.findViewById(R.id.lv_articel);
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.ui_footview, null);
        lv_articel.addFooterView(footer);
        article_pb = (ProgressBar) view.findViewById(R.id.article_pb);
        adapter = new ArticleAdapter(articles, getActivity());
        lv_articel.setAdapter(adapter);
        lv_articel.removeFooterView(footer);
        lv_articel.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewsDetail.class);
                intent.putExtra("url", articles.get(position).getUrl());
                intent.putExtra("index", 2);
                getActivity().startActivity(intent);

            }
        });

        lv_articel.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 当不滚动时
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                adapter.resumeLoad();
                break;
            case SCROLL_STATE_FLING:
                adapter.pauseLoad();
                break;
            default:
                break;
        }


        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            // 判断是否滚动到底部  
            if (view.getLastVisiblePosition() == view.getCount() - 1) {
                //加载更多功能的代码  
                lv_articel.addFooterView(footer);
                ++page;
                initData(page);

            }
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub

    }

}
