package com.iknown.ylf.iknown.activity;

import android.app.Activity;
import android.content.Intent;
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
    private  Button btn_baidu;
    private  Button btn_telent;
    private  Button iv_finish;
    private  ImageView iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_ad_main);
        initView();
    }

    private void initView() {
        btn_baidu= (Button) findViewById(R.id.btn_baidu);
        btn_telent= (Button) findViewById(R.id.btn_telent);
        iv_finish= (Button) findViewById(R.id.iv_finish);
        iv_logo= (ImageView) findViewById(R.id.iv_logo);
        iv_logo.setVisibility(View.GONE);
        iv_finish.setVisibility(View.VISIBLE);
        iv_finish.setOnClickListener(this);
        btn_telent.setOnClickListener(this);
        btn_baidu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btn_telent:
                startActivity(new Intent(this,TelentAds.class));
                break;
            case  R.id.btn_baidu:
                startActivity(new Intent(this,BaiduAds.class));
                break;
            case  R.id.iv_finish:
                this.finish();
                break;
        }

    }
}
