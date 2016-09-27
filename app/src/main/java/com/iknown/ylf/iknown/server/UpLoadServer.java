package com.iknown.ylf.iknown.server;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.iknown.ylf.iknown.receiver.DownLoadReceiver;
import com.iknown.ylf.iknown.utils.DownLoadUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by ylf on 2016/9/18.
 */
public class UpLoadServer extends Service {
    private String url;
    private DownloadManager manager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        url=intent.getStringExtra("url");
         DownLoadUtils utils=DownLoadUtils.getInstance(this,url);
         manager=utils.getManager();



        DownLoadReceiver receiver=new DownLoadReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                Toast.makeText(UpLoadServer.this, "下载完成", Toast.LENGTH_SHORT).show();

                String downloadPath = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + DownLoadUtils.getVersionName();
                //installAPK(new File(downloadPath));、
                SharedPreferences preferences=context.getSharedPreferences("downLoadId",Context.MODE_PRIVATE);
                long id = preferences.getLong("download_id", 0);
                try {
                    manager.openDownloadedFile(id);
                    File f=new File(downloadPath);
                    installAPK(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                stopSelf();
            }
        };

        IntentFilter filter=new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        this.registerReceiver(receiver,filter);
    }

    //安装应用，指定位置
    private void installFile(String path) {
      /*  SharedPreferences preferences=this.getSharedPreferences("downLoadId",Context.MODE_PRIVATE);
        long id = preferences.getLong("download_id", 0);*/
        Intent install = new Intent(Intent.ACTION_VIEW);
     /*   Uri downloadFileUri = manager.getUriForDownloadedFile(id);
        String path=downloadFileUri.getPath();
        Toast.makeText(UpLoadServer.this, "p:"+path, Toast.LENGTH_SHORT).show();//mydownload11*/
        install.setDataAndType(Uri.parse(path), "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(install);
    }

    protected void installAPK(File file) {
        if (!file.exists())
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

}
