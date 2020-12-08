package com.projectlite2.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.GuideActivity;
import com.projectlite2.android.activity.LoginActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.UserInfoSaveSharedPreference;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTransparent(SplashActivity.this);

        //
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent it;
                if (!UserInfoSaveSharedPreference.getPrefNotFirstUse(MyApplication.getContext())){
                    it=new Intent(getApplicationContext(), MyWelcomeActivity.class);//启动WelcomeActivity
                }else{
                    it=new Intent(getApplicationContext(), LoginActivity.class);//启动LoginActivity
//                         it = new Intent(MyApplication.getContext(), MainActivity.class);

                }
                startActivity(it);
                finish();//关闭当前活动
            }
        }, 1000);//1秒后执行Runnable中的run方法



    }
}

