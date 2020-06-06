package com.rightpoint.ipceventbus;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

/**
 * Description：
 * @author Wonder Wei
 * Create date：2020/6/5 10:55 AM 
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().init(this);
    }
}
