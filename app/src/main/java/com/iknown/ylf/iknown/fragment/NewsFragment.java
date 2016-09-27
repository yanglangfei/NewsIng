package com.iknown.ylf.iknown.fragment;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.iknown.ylf.iknown.R;
import com.iknown.ylf.iknown.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private View view;

    private RadioGroup group;
    private ProgressBar pBar;
    private ViewPager viewpager;

    private String type[] = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技",
            "财经", "时尚"};

    private  List<Fragment> fragmentList=new ArrayList<>();
    private MainAdapter adapter;
    private NewsTabFragment tabFragment;
    private HorizontalScrollView hsv;
    private TextView tv_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ui_news_list, container, false);
        initView();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_title=(TextView)getActivity().findViewById(R.id.tv_title);
        tv_title.setVisibility(View.GONE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        hsv= (HorizontalScrollView) view.findViewById(R.id.hsv);

        adapter=new MainAdapter(getChildFragmentManager(),fragmentList);
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(this);


        group = (RadioGroup) view.findViewById(R.id.news_group);
        pBar = (ProgressBar) view.findViewById(R.id.pb);
        group.removeAllViews();
        fragmentList.clear();
        for (int i = 0; i < type.length; i++) {
            RadioButton button = new RadioButton(getActivity());
            button.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            button.setTextColor(Color.BLACK);
            button.setGravity(Gravity.CENTER);
            button.setPadding(10, 10, 10, 10);
            LayoutParams params = new LayoutParams(150, LayoutParams.MATCH_PARENT);
            button.setLayoutParams(params);
            button.setId(i);
            button.setText(type[i]);
         //   button.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.news_tab_laylist));
            group.addView(button);

            tabFragment=new NewsTabFragment();
            Bundle build=new Bundle();
            build.putInt("index",i);
            tabFragment.setArguments(build);
            fragmentList.add(tabFragment);
        }
        adapter.notifyDataSetChanged();

        group.setOnCheckedChangeListener(this);
        if (group.getChildCount() > 0) {
            RadioButton button = (RadioButton) group.getChildAt(0);
            button.setChecked(true);
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        viewpager.setCurrentItem(checkedId);


        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton radioButton= (RadioButton) group.getChildAt(i);
            if(radioButton.isChecked()){
                radioButton.setTextColor(Color.parseColor("#cc3636"));
            }else {
                radioButton.setTextColor(Color.BLACK);
            }

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton button= (RadioButton) group.getChildAt(position);
        button.setChecked(true);
        hsv.scrollTo(button.getLeft(),0);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}

