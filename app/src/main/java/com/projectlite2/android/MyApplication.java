package com.projectlite2.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import cn.leancloud.chatkit.LCChatKit;

import static androidx.navigation.Navigation.findNavController;

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static NavController navController;

    //   ************ 国际版 *************
//    private String _appId = "xCPMuwMtvrHTaRYE3sdnlBez-MdYXbMMI";
//    private String _appKey = "nJY5JvhpKI3Nkcob3IevMNr3";
//    private String _serverUrl = "xCPMuwMt.api.lncldglobal.com";
//   *****************************


    //   ************ 华北版 *************
    private String _appId = "w1vGF0c7QirnF5rFE0CXXFvs-gzGzoHsz";
    private String _appKey = "t2FWWwG6j1hOgRa26Prv1mP1";
       private String _serverUrl ="sms.tencentcloudapi.com";
//   *****************************


    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();

        // 在 AVOSCloud.initialize() 之前调用
        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);

        AVOSCloud.initialize(this, _appId, _appKey, _serverUrl);

        //  初始化ChatKit
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(mContext, _appId, _appKey, _serverUrl);


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

    public static void navJump(View view, @IdRes int resId) {
        navController = findNavController(view);
        navController.navigate(resId);
    }

    public static void navJump(View view, @IdRes int resId, @Nullable Bundle bundle) {
        navController = findNavController(view);
        navController.navigate(resId, bundle);
    }

}