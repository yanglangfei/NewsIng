package com.iknown.ylf.iknown;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.iknown.ylf.iknown.activity.AdviseActivice;
import com.iknown.ylf.iknown.activity.BaseActivity;
import com.iknown.ylf.iknown.fragment.ArticleFragment;
import com.iknown.ylf.iknown.fragment.NewsFragment;
import com.iknown.ylf.iknown.model.ApkManager;
import com.iknown.ylf.iknown.server.UpLoadServer;
import com.iknown.ylf.iknown.utils.DeviceUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private Button iv_finish;
    private FragmentTabHost tabHost;
    private  Button btn_more;

    private String tabs[] = {"资讯", "微信精选"};
    private int tab_drawables[] = {R.drawable.select_zixun, R.drawable.select_jingxuan};
    private Class fragments[] = {NewsFragment.class, ArticleFragment.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#cc3636"));
        }
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.main_lay);
        btn_more= (Button) findViewById(R.id.btn_more);
        btn_more.setVisibility(View.VISIBLE);
        btn_more.setOnClickListener(this);

        for (int i = 0; i < tabs.length; i++) {
            TabHost.TabSpec tab = tabHost.newTabSpec(tabs[i]);
            View view = LayoutInflater.from(this).inflate(R.layout.ui_tab_item, null);
            TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);
            ImageView iv_tab = (ImageView) view.findViewById(R.id.iv_tab);
            tv_tab.setText(tabs[i]);
            iv_tab.setImageResource(tab_drawables[i]);
            tab.setIndicator(view);
            tabHost.addTab(tab, fragments[i], null);
        }


        iv_finish = (Button) findViewById(R.id.iv_finish);
        iv_finish.setVisibility(View.GONE);

        DeviceUtils utils = DeviceUtils.getInstance(this);
       /* ApkManager manager=new ApkManager();
        manager.setApkUrl("");
        manager.setPackageName("");
        manager.setVersionCode(1);
        manager.setVersionName("1.1");
        manager.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Toast.makeText(MainActivity.this, "ss", Toast.LENGTH_SHORT).show();
            }
        });*/


        int currentCode = utils.getVersionCode();
        checkVersionCode(currentCode);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 两秒之内改变退出状态
        } else {
            //退出程序
            // ApkUtil.finishActivity();
            System.exit(0);
            finish();

        }

    }

    private void checkVersionCode(final int currentCode) {
        BmobQuery<ApkManager> apkInfo = new BmobQuery<>();
        apkInfo.order("-createdAt");
        apkInfo.setLimit(1);
        apkInfo.findObjects(new FindListener<ApkManager>() {
            @Override
            public void done(List<ApkManager> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        ApkManager apk = list.get(0);
                        int code = apk.getVersionCode();
                        if (currentCode < code) {
                            //更新apk
                            String apkUrl = apk.getApkUrl();
                            Intent intent = new Intent(MainActivity.this, UpLoadServer.class);
                            intent.putExtra("url", apkUrl);
                            MainActivity.this.startService(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        MainActivity.this.startActivity(new Intent(MainActivity.this,AdviseActivice.class));
    }
}
