package com.projectlite2.android;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();
    }
    public static Context getContext(){
        return mContext;
    }
}