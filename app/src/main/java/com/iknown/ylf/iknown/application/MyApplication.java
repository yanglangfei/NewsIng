package com.iknown.ylf.iknown.application;
import org.xutils.x;
import android.app.Application;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AppActivity;
import com.bugtags.library.Bugtags;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application{

	private static MyApplication INSTANCE;
	private String appkey="bc80663f76526523395616eccb98528c";
	private String BugKey="49c077058e48aa023e10bfad167684cf";
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

		//Bugtags.start(BugKey,this,Bugtags.BTGInvocationEventBubble);

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


}
