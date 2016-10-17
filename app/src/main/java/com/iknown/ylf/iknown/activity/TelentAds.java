package com.iknown.ylf.iknown.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.iknown.ylf.iknown.R;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class TelentAds extends Activity implements View.OnClickListener {
    private  String telentId[]={"2030815586523964","3000512506032172","1050713546730123"};
    private List<RelativeLayout> telentViews=new ArrayList<>();
    private ProgressBar pb_advice;
    private ImageView iv_logo;
    private Button iv_finish;
    private String appId="1105632623";
    private String postId="2030815586523964";
    private RelativeLayout lay1;
    private RelativeLayout lay2;
    private RelativeLayout lay3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_telent_ads);
        initView();
        initTelentAdData();
    }

    private void initView() {
        iv_finish= (Button) findViewById(R.id.iv_finish);
        iv_finish.setVisibility(View.VISIBLE);
        iv_logo= (ImageView) findViewById(R.id.iv_logo);
        pb_advice= (ProgressBar) findViewById(R.id.pb_advice);
        pb_advice.setVisibility(View.VISIBLE);
        iv_logo.setVisibility(View.GONE);
        iv_finish.setOnClickListener(this);
        lay1= (RelativeLayout) findViewById(R.id.adv1);
        lay2= (RelativeLayout) findViewById(R.id.adv2);
        lay3= (RelativeLayout) findViewById(R.id.adv3);
        telentViews.add(lay1);
        telentViews.add(lay2);
        telentViews.add(lay3);
    }
    /**
     *  获取腾讯广告数据
     */
    public void initTelentAdData(){
        for (int i = 0; i < telentId.length; i++) {
            BannerView bannerView=new BannerView(this, ADSize.BANNER,appId,postId);
            telentViews.get(i).addView(bannerView);
            bannerView.setRefresh(30);
            bannerView.setADListener(new AbstractBannerADListener() {
                @Override
                public void onNoAD(int i) {

                }

                @Override
                public void onADReceiv() {
                    pb_advice.setVisibility(View.GONE);


                }
            });
            bannerView.loadAD();
        }
    }


    @Override
    public void onClick(View view) {
        this.finish();
    }
}
