package com.iknown.ylf.iknown.activity;

import android.app.Activity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;

/**
 * Created by Administrator on 2016/9/27.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onRestart() {
        super.onRestart();
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }
}
