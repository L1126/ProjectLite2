package com.projectlite2.android.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;

import com.projectlite2.android.CustomUserProvider;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.push.PushService;
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
    private String _serverUrl = "https://w1vgf0c7.lc-cn-n1-shared.com";
//   *****************************


    public static String MY_INSTALLATION_ID = "";


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


        //  订阅某个频道（channel）的消息，只要在保存 Installation 之前调用 PushService.subscribe 方法
        //  回调对象指用户点击通知栏的通知进入的 Activity 页面
        // 订阅频道，当该频道消息到来的时候，打开对应的 Activity
        // 参数依次为：当前的 context、频道名称、回调对象的类
//        PushService.subscribe(this, "public", PushDemo.class);
//        PushService.subscribe(this, "private", Callback1.class);
//        PushService.subscribe(this, "protected", Callback2.class);

        //  退订
//        PushService.unsubscribe(context, "protected");
//        //退订之后需要重新保存 Installation
//        AVInstallation.getCurrentInstallation().saveInBackground();


        //创建新的channel
        NotificationManager mNotiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "default", NotificationManager.IMPORTANCE_HIGH);
            mNotiManager.createNotificationChannel(channel);
        }


        // 设置默认的channel
        PushService.setDefaultChannelId(this, "default");


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
     *
     * @param s 显示文字
     */
    public static void ToastySuccess(String s) {
        Toasty.success(MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 显示 Toasty warning 工具方法
     *
     * @param s 显示文字
     */
    public static void ToastyWarning(String s) {
        Toasty.warning(MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 显示 Toasty error 工具方法
     *
     * @param s 显示文字
     */
    public static void ToastyError(String s) {
        Toasty.error(MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 显示 Toasty info  工具方法
     *
     * @param s 显示文字
     */
    public static void ToastyInfo(String s) {
        Toasty.info(MyApplication.getContext(), s, Toast.LENGTH_SHORT, true).show();
    }


    /**
     * navigation跳转
     *
     * @param view
     * @param resId
     */
    public static void navJump(View view, @IdRes int resId) {
        navController = findNavController(view);
        navController.navigate(resId);
    }

    /**
     * 传递参数的navigation跳转
     *
     * @param view
     * @param resId
     * @param bundle
     */
    public static void navJump(View view, @IdRes int resId, @Nullable Bundle bundle) {
        navController = findNavController(view);
        navController.navigate(resId, bundle);
    }

    /**
     * 通用判断是否为合法手机号
     *
     * @param telNum
     * @return
     */
    public static boolean isMobilePhoneNum(String telNum) {
        String regex = "^1[3-9]\\d{9}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }


    /**
     * 生成随机的12位id
     *
     * @return id号码
     */
    public static String BuildRandomID() {

        String val = "";
        Random random = new Random();
        //length为几位密码
        for (int i = 0; i < 12; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


    /**
     * 软键盘显示/隐藏
     */
    public static void hideShowKeyboard(Context ctx) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE); //得到InputMethodManager的实例
        if (imm.isActive()) {//如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }



}