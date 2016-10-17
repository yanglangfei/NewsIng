package com.iknown.ylf.iknown.application;
import org.xutils.x;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baidu.mobads.AppActivity;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application{

	private static MyApplication INSTANCE;
	private String appkey="bc80663f76526523395616eccb98528c";
	public static MyApplication INSTANCE(){
		return INSTANCE;
	}
	private void setInstance(MyApplication app) {
		setBmobIMApplication(app);
	}
	private static void setBmobIMApplication(MyApplication a) {
		MyApplication.INSTANCE = a;
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		x.Ext.setDebug(true);
		// 在主进程设置信鸽相关的内容
		if (isMainProcess()) {
			// 为保证弹出通知前一定调用本方法，需要在application的onCreate注册
			// 收到通知时，会调用本回调函数。
			// 相当于这个回调会拦截在信鸽的弹出通知之前被截取
			// 一般上针对需要获取通知内容、标题，设置通知点击的跳转逻辑等等
			XGPushManager
					.setNotifactionCallback(new XGPushNotifactionCallback() {

						@Override
						public void handleNotify(XGNotifaction xGNotifaction) {
							Log.i("test", "处理信鸽通知：" + xGNotifaction);
							// 获取标签、内容、自定义内容
							String title = xGNotifaction.getTitle();
							String content = xGNotifaction.getContent();
							String customContent = xGNotifaction
									.getCustomContent();
							// 其它的处理
							// 如果还要弹出通知，可直接调用以下代码或自己创建Notifaction，否则，本通知将不会弹出在通知栏中。
							xGNotifaction.doNotify();
						}
					});
		}
		AppActivity.setActionBarColorTheme(AppActivity.ActionBarColorTheme.ACTION_BAR_RED_THEME);

		if (getApplicationInfo().packageName.equals(getMyProcessName())) {
			Bmob.initialize(this, appkey);
		}

	}

	/**
	 * 获取当前运行的进程名
	 *
	 * @return
	 */
	public static String getMyProcessName() {
		try {
			File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
			BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
			String processName = mBufferedReader.readLine().trim();
			mBufferedReader.close();
			return processName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isMainProcess() {
		ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
		List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = getPackageName();
		int myPid = android.os.Process.myPid();
		for (ActivityManager.RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}


}
