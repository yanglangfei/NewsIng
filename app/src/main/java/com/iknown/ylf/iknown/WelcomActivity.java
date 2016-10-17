package com.iknown.ylf.iknown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.mobads.SplashAd;
import com.baidu.mobads.SplashAdListener;
import com.iknown.ylf.iknown.activity.MainActivity;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

/**
 * Created by Administrator on 2016/10/17.
 */

public class WelcomActivity extends Activity {
    private String adId="2989044";
    private LinearLayout lay_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_welcom);
        initView();
    }

    private void initView() {
        lay_ad= (LinearLayout) findViewById(R.id.lay_ad);
        SplashAdListener listener=new SplashAdListener() {
            @Override
            public void onAdPresent() {

            }

            @Override
            public void onAdDismissed() {
                WelcomActivity.this.startActivity(new Intent(WelcomActivity.this, MainActivity.class));

            }

            @Override
            public void onAdFailed(String s) {
                WelcomActivity.this.startActivity(new Intent(WelcomActivity.this, MainActivity.class));

            }

            @Override
            public void onAdClick() {
            }
        };
        new SplashAd(this,lay_ad,listener,adId);
    }
}
