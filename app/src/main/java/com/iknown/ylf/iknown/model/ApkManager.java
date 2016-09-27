package com.iknown.ylf.iknown.model;

import java.io.File;

import cn.bmob.v3.BmobObject;

/**
 * Created by ylf on 2016/9/18.
 */
public class ApkManager extends BmobObject {

    private  int id;

    private String versionName;

    private  int versionCode;

    private  String packageName;

    private  String apkUrl;

    private  File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }
}
