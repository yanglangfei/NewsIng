package com.iknown.ylf.iknown.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.iknown.ylf.iknown.receiver.DownLoadReceiver;

/**
 * Created by ylf on 2016/9/18.
 */
public class DownLoadUtils {

    private static DownLoadUtils utils;
    private static DownloadManager manager;
    private static String versionName = "newsImage";

    private DownLoadUtils() {

    }

    public static String getVersionName() {
        return versionName;
    }

    public static DownLoadUtils getInstance(Context context, String url) {
        if (utils == null) {
            utils = new DownLoadUtils();
            initDownLoadManager(context, url);
        }
        return utils;
    }

    public static DownloadManager getManager() {
        return manager;
    }

    private static void initDownLoadManager(Context context, String url) {
        SharedPreferences preferences = context.getSharedPreferences("downLoadId", Context.MODE_PRIVATE);
        manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (url == null) {
            return;
        }
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        request.setMimeType(mimeString);
        request.setTitle("apk更新");
        request.setVisibleInDownloadsUi(true);

     /*   path = Environment.getExternalStorageState() + "/myapk";

        request.setDestinationInExternalFilesDir(context, mimeString, path);*/

        request.setDestinationInExternalPublicDir("/dowmload", versionName);


        long myid = manager.enqueue(request);
        preferences.edit().putLong("download_id", myid).commit();
        IntentFilter intent = new IntentFilter();
        intent.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        DownLoadReceiver receiver = new DownLoadReceiver();
        context.registerReceiver(receiver, intent);
    }
}
