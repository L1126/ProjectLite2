package com.projectlite2.android.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.projectlite2.android.CustomUserProvider;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import cn.leancloud.chatkit.LCChatKit;
import es.dmoral.toasty.Toasty;

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
    private String _serverUrl ="https://w1vgf0c7.lc-cn-n1-shared.com";
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

    //获取全局Context的静态方法
    public static Context getContext() {
        return mContext;
    }

    //Toast工具方法-String
    public static void showToast(String s) {
        Toast.makeText(MyApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }
    //Toast工具方法-Int
    public static void showToast(Integer i) {
        Toast.makeText(MyApplication.getContext(), i, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示 Toasty success 工具方法
     * @param s 显示文字
     */
    public static void ToastySuccess(String s){
        Toasty.success(MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 显示 Toasty warning 工具方法
     * @param s 显示文字
     */
    public static void ToastyWarning(String s){
        Toasty.warning(MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 显示 Toasty error 工具方法
     * @param s 显示文字
     */
    public static void ToastyError(String s){
        Toasty.error(MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 显示 Toasty info  工具方法
     * @param s 显示文字
     */
    public static void ToastyInfo(String s){
        Toasty.info (MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
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