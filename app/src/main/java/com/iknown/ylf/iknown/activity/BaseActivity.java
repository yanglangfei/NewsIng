package com.iknown.ylf.iknown.activity;

import android.app.Activity;
import android.view.MotionEvent;
/**
 * Created by Administrator on 2016/9/27.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
