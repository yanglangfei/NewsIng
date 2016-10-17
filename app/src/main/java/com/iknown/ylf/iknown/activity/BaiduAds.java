package com.iknown.ylf.iknown.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.iknown.ylf.iknown.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class BaiduAds extends Activity implements View.OnClickListener {
    private RelativeLayout lay1,lay2,lay3,lay4,lay5,lay6,lay7,lay8;
    //百度
    private  String adviceId[]={"2961716","2930327","2930328","2930329","2930331","2930332","2930374","2930375"};
    private List<RelativeLayout> baiduViews=new ArrayList<>();
    private ProgressBar pb_advice;
    private ScrollView advice_lay;
    private ImageView iv_logo;
    private Button iv_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_baidu_ads);
        initView();
        initBaiduData();
    }

    private void initBaiduData() {
        for (int i = 0; i < adviceId.length; i++) {
            initAdvice(baiduViews.get(i),adviceId[i]);
        }

    }

    private void initAdvice(RelativeLayout relativeLayout,String adviceId) {
        //人群属性
        AdSettings.setKey(new String[]{"baidu", "中国"});
        //创建广告view
        AdView adView = new AdView(this, adviceId);
        //设置监听器
        adView.setListener(new AdViewListener() {
            @Override
            public void onAdReady(AdView adView) {

            }

            @Override
            public void onAdShow(JSONObject jsonObject) {
                pb_advice.setVisibility(View.GONE);
                advice_lay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClick(JSONObject jsonObject) {

            }

            @Override
            public void onAdFailed(String s) {
            }

            @Override
            public void onAdSwitch() {
            }

            @Override
            public void onAdClose(JSONObject jsonObject) {
            }
        });
        relativeLayout.addView(adView);
    }

    private void initView() {
        iv_finish= (Button) findViewById(R.id.iv_finish);
        iv_finish.setVisibility(View.VISIBLE);
        iv_logo= (ImageView) findViewById(R.id.iv_logo);
        advice_lay= (ScrollView) findViewById(R.id.advice_lay);
        advice_lay.setVisibility(View.GONE);
        pb_advice= (ProgressBar) findViewById(R.id.pb_advice);
        pb_advice.setVisibility(View.VISIBLE);
        iv_logo.setVisibility(View.GONE);
        iv_finish.setOnClickListener(this);
        lay1= (RelativeLayout) findViewById(R.id.adv1);
        lay2= (RelativeLayout) findViewById(R.id.adv2);
        lay3= (RelativeLayout) findViewById(R.id.adv3);
        lay4= (RelativeLayout) findViewById(R.id.adv4);
        lay5= (RelativeLayout) findViewById(R.id.adv5);
        lay6= (RelativeLayout) findViewById(R.id.adv6);
        lay7= (RelativeLayout) findViewById(R.id.adv7);
        lay8= (RelativeLayout) findViewById(R.id.adv8);
        baiduViews.add(lay1);
        baiduViews.add(lay2);
        baiduViews.add(lay3);
        baiduViews.add(lay4);
        baiduViews.add(lay5);
        baiduViews.add(lay6);
        baiduViews.add(lay7);
        baiduViews.add(lay8);
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
