package com.bwei.dabian;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyAppclicatioin extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mContext=getApplicationContext();
    }

    public static Context getApplication() {
        return mContext;
    }
}
