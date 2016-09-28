package com.iknown.ylf.iknown.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iknown.ylf.iknown.R;
import com.iknown.ylf.iknown.utils.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
/**
 * Created by Administrator on 2016/9/28.
 */
public class HistoryDetail  extends Activity implements View.OnClickListener {
    private TextView historyDetailTitle;
    private  TextView historyTime;
    private  TextView historyDetailDesc;
    private  TextView historyContent;
    private Button iv_finish;
    private  ImageView iv_logo;
    private  TextView tv_title;
    private ProgressBar pb;
    private  String getHistoryDetail="http://api.juheapi.com/japi/tohdet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_history_detail);
        initView();
        initData();
    }

    private void initData() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        String id=getIntent().getStringExtra("id");
        RequestParams param=new RequestParams(getHistoryDetail);
        param.addParameter("v","1.0");
        param.addParameter("id",id);
        param.addParameter("key", Contact.HISTORYKEY);
        x.http().get(param, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String s) {
                return false;
            }

            @Override
            public void onSuccess(String s) {
                if(s!=null){
                    pb.setVisibility(View.GONE);
                    try {
                        JSONObject object=new JSONObject(s);
                        int error_code=object.getInt("error_code");
                        if(error_code==0){
                            JSONArray array=object.getJSONArray("result");
                            JSONObject object1=array.getJSONObject(0);
                            String title=object1.optString("title","");
                            String des=object1.optString("des","");
                            int year=object1.optInt("year");
                            int month=object1.optInt("month");
                            int day=object1.optInt("day");
                            String content=object1.optString("content","");
                            historyDetailTitle.setText(title);
                            historyDetailDesc.setText(des);
                            historyTime.setText(year+"年"+month+"月"+day+"日");
                            historyContent.setText(content);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onError(Throwable throwable, boolean b) {

            }
            @Override
            public void onCancelled(CancelledException e) {

            }
            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        historyDetailTitle= (TextView) findViewById(R.id.historyDetailTitle);
        historyTime= (TextView) findViewById(R.id.historyTime);
        historyDetailDesc= (TextView) findViewById(R.id.historyDetailDesc);
        historyContent= (TextView) findViewById(R.id.historyContent);
        iv_finish= (Button) findViewById(R.id.iv_finish);
        iv_logo= (ImageView) findViewById(R.id.iv_logo);
        pb= (ProgressBar) findViewById(R.id.pb);
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText("今日历史");
        pb.setVisibility(View.VISIBLE);
        iv_logo.setVisibility(View.GONE);
        iv_finish.setVisibility(View.VISIBLE);
        iv_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.finish();
    }
}
