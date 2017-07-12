package com.example.guanhuawu.directory_demo;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by guanhua.wu on 2017/7/12.
 */

public class MyApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Log.e("application","create success");
        LeakCanary.install(this);
    }
}
