package com.example.demo8_30;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    class MyBinder extends Binder{

    }
    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "com.example.demo8_30.pre") {
                Log.i("test", "上一首");
            }
            if (intent.getAction() == "com.example.demo8_30.pause"){
                Log.i("test", "暂停");
            }
            if (intent.getAction() == "com.example.demo8_30.pause"){
                Log.i("test", "下一首");
            }
            if (intent.getAction() == "com.example.demo8_30.pause"){
                Log.i("test", "销毁");
                stopForeground(true);
            }
        }
    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("test","bind.....");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        Log.i("test","create.....");
        super.onCreate();
        Notification notification;
        PendingIntent activityPendingIntent;
        PendingIntent prePendingIntent;
        PendingIntent pausePendingIntent;
        PendingIntent nextPendingIntent;
        PendingIntent destoryPendingIntent;
        activityPendingIntent = PendingIntent.getActivity(this,8848,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        prePendingIntent = PendingIntent.getBroadcast(this, 6666,new Intent("com.example.demo8_30.pre"),PendingIntent.FLAG_UPDATE_CURRENT);
        pausePendingIntent = PendingIntent.getBroadcast(this, 7777,new Intent("com.example.demo8_30.pause"),PendingIntent.FLAG_UPDATE_CURRENT);
        nextPendingIntent = PendingIntent.getBroadcast(this, 8888,new Intent("com.example.demo8_30.next"),PendingIntent.FLAG_UPDATE_CURRENT);
        destoryPendingIntent = PendingIntent.getBroadcast(this, 9999,new Intent("com.example.demo8_30.destory"),PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder= new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_notification)
                .setContentIntent(activityPendingIntent)
                .setContentTitle("title")
                .setContentText("text")
                .addAction(R.mipmap.ic_play_btn_prev_1,"上一首",prePendingIntent)
                .addAction(R.mipmap.ic_play_btn_pause1,"暂停/继续",pausePendingIntent)
                .addAction(R.mipmap.ic_play_btn_next_1,"下一首",nextPendingIntent)
                .addAction(R.mipmap.btn_destroy,"销毁",destoryPendingIntent)
                .setStyle(new Notification.MediaStyle());
        notification = builder.build();
        startForeground(1024, notification);
        MyReceiver myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.demo8_30.pre");
        intentFilter.addAction("com.example.demo8_30.pause");
        intentFilter.addAction("com.example.demo8_30.next");
        intentFilter.addAction("com.example.demo8_30.destory");
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        Log.i("test","destory.....");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("test","unbind.....");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("test","startcommand.....");
        return super.onStartCommand(intent, flags, startId);
    }
}
