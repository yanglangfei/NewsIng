package com.iknown.ylf.iknown.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iknown.ylf.iknown.activity.NewsDetail;
import com.iknown.ylf.iknown.R;
import com.iknown.ylf.iknown.adapter.NewsAdapter;
import com.iknown.ylf.iknown.model.News;
import com.iknown.ylf.iknown.utils.Contact;
import com.iknown.ylf.iknown.utils.JsonUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class NewsTabFragment extends Fragment {

    private View view;

    private ListView news_list;
    private static final String GET_NEWS = "http://v.juhe.cn/toutiao/index";
    private NewsAdapter adapter;
    private ViewPager viewpager;
    private List<News> newsArray = new ArrayList<News>();
    // top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    private String typeArray[] = {"top", "shehui", "guonei", "guoji", "yule",
            "tiyu", "junshi", "keji", "caijing", "shishang"};
    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.ui_news_tab,container,false);
        initView();
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        index=getArguments().getInt("index");
        if(newsArray==null){
            newsArray=new ArrayList<>();
        }
        newsArray.clear();
        initData(typeArray[index]);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        news_list = (ListView) view.findViewById(R.id.news_list);
        viewpager= (ViewPager) view.findViewById(R.id.viewpager);

        adapter = new NewsAdapter(newsArray, getActivity());
        news_list.setAdapter(adapter);
        news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewsDetail.class);
                intent.putExtra("url", newsArray.get(position).getUrl());
                intent.putExtra("index",1);
                getActivity().startActivity(intent);

            }
        });

        news_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
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


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    public void initData(String typeStr) {
        news_list.setVisibility(View.GONE);
        RequestParams param = new RequestParams(GET_NEWS);
        param.addParameter("type", typeStr);
        param.addParameter("key", Contact.NEWS_KEY);
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
                if (arg0 != null) {
                    newsArray = JsonUtils.getNewsList(arg0);
                }
                adapter.setNewsArray(newsArray);
                adapter.notifyDataSetChanged();
                news_list.setVisibility(View.VISIBLE);

            }

            @Override
            public boolean onCache(String arg0) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }
}
