package com.iknown.ylf.iknown.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

/**
 * Created by ylf on 2016/9/18.
 */
public class DeviceUtils {

    private  static  DeviceUtils utils;
    private static PackageManager manager;
    private static PackageInfo info;

    private  DeviceUtils(){
    }

    public  static  DeviceUtils getInstance(Context context){
        if(utils==null){
            utils=new DeviceUtils();
            initManager(context);
        }
        return  utils;
    }

    private static void initManager(Context context) {
         manager=context.getPackageManager();
        try {
            info=manager.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return   获取版本号
     */
    public int getVersionCode(){
        return  info.versionCode;
    }

    public  String getVersionName(){
        return  info.versionName;
    }


}
