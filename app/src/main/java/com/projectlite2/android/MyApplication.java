package com.projectlite2.android;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;

public class MyApplication extends Application {

    private static Context mContext;

    private String _appId= "xCPMuwMtvrHTaRYE3sdnlBez-MdYXbMMI";
    private String _appKey  = "nJY5JvhpKI3Nkcob3IevMNr3";
    private String _serverUrl = "xCPMuwMt.api.lncldglobal.com";


    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();

        // 在 AVOSCloud.initialize() 之前调用
        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);

        AVOSCloud.initialize(this, _appId, _appKey,_serverUrl );

    }

    public static Context getContext() {
        return mContext;
    }

    public static void showToast(String s) {
        Toast.makeText(MyApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Integer i) {
        Toast.makeText(MyApplication.getContext(), i, Toast.LENGTH_SHORT).show();
    }

}