package com.example.yun.daemonservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.coolerfall.daemon.Daemon;
import com.example.yun.daemonservicedemo.daemon.DaemonService;
import com.example.yun.daemonservicedemo.daemon.JobService;

/**
 * Created by hyy on 2018/2/26.
 * this is a daemon service and can keep alive in background
 */

public class MainService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();


        DaemonService.setForeground(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobService.start(this);
        } else {
            Daemon.run(this, MainService.class, Daemon.INTERVAL_ONE_MINUTE * 2);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
