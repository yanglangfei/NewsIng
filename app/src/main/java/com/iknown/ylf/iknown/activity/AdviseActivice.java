package com.iknown.ylf.iknown.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.comm.net.rr.Request;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016/9/27.
 */
public class AdviseActivice extends Activity implements View.OnClickListener {
    private  RelativeLayout lay1,lay2,lay3,lay4,lay5,lay6,lay7,lay8;
    private  String adviceId[]={"2930323","2930327","2930328"};
    private List<RelativeLayout> views=new ArrayList<>();
    private ProgressBar pb_advice;
    private ScrollView advice_lay;
    private ImageView iv_logo;
    private Button iv_finish;
    private String appId="1105738142";
    private String postId="7060412555988800";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_advise);
        initView();
        initData();
        initTelentAdData();
    }

    /**
     *  获取腾讯广告数据
     */
    public void initTelentAdData(){
        BannerView bannerView=new BannerView(this, ADSize.BANNER,appId,postId);
        views.get(3).addView(bannerView);
        bannerView.setRefresh(3000);
        bannerView.setADListener(new AbstractBannerADListener() {
            @Override
            public void onNoAD(int i) {
                Log.i("111","noAd:"+i);
            }

            @Override
            public void onADReceiv() {
                Log.i("111","receiverAd");

            }
        });
        bannerView.loadAD();
    }



    /*
       获取百度移动广告
     */
    private void initData() {
        for (int i = 0; i < adviceId.length; i++) {
            initAdvice(views.get(i),adviceId[i]);
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
        views.add(lay1);
        views.add(lay2);
        views.add(lay3);
        views.add(lay4);
        views.add(lay5);
        views.add(lay6);
        views.add(lay7);
        views.add(lay8);
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
