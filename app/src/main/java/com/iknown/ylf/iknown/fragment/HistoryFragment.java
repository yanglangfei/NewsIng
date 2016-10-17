package com.iknown.ylf.iknown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.iknown.ylf.iknown.R;
import com.iknown.ylf.iknown.activity.HistoryDetail;
import com.iknown.ylf.iknown.adapter.HistoryAdapter;
import com.iknown.ylf.iknown.model.History;
import com.iknown.ylf.iknown.utils.Contact;
import com.iknown.ylf.iknown.utils.TimeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */

public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View view;
    private ListView lv_history;
    private HistoryAdapter adapter;
    private List<History> histories=new ArrayList<>();
    private TextView tv_date;
    private ProgressBar pb;
    private  TextView tv_week;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月yy日");
    private  String historyPath="http://api.juheapi.com/japi/toh";
    private int month;
    private int day;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ui_history_list,container,false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        RequestParams param=new RequestParams(historyPath);
        param.addParameter("v","1.0");
        param.addParameter("month",month);
        param.addParameter("day",day);
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
                   lv_history.setVisibility(View.VISIBLE);
                   try {
                       JSONObject object=new JSONObject(s);
                       JSONArray result=object.getJSONArray("result");
                       for (int i = 0; i < result.length(); i++) {
                           JSONObject his=result.getJSONObject(i);
                           String _id=his.optString("_id","");
                           String title=his.optString("title","");
                           String pic=his.optString("pic","");
                           String des=his.optString("des","");
                           History history=new History();
                           history.set_id(_id);
                           history.setTitle(title);
                           history.setPic(pic);
                           history.setDes(toDBC(des));
                           histories.add(history);
                       }
                       adapter.notifyDataSetChanged();
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

    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i< c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }if (c[i]> 65280&& c[i]< 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    private void initView() {
        Date date=new Date();
        month=date.getMonth()+1;
        day=date.getDate();
        lv_history= (ListView) view.findViewById(R.id.lv_history);
        tv_date= (TextView) view.findViewById(R.id.tv_date);
        tv_date.setText(sdf.format(new Date()));
        tv_week= (TextView) view.findViewById(R.id.tv_week);
        tv_week.setText(TimeUtils.getWeek());
        adapter=new HistoryAdapter(histories,getActivity());
        lv_history.setAdapter(adapter);
        lv_history.setOnItemClickListener(this);
        pb= (ProgressBar) view.findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        lv_history.setVisibility(View.GONE);
        lv_history.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
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
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(), HistoryDetail.class);
        intent.putExtra("id",histories.get(i).get_id());
        startActivity(intent);
    }
}
