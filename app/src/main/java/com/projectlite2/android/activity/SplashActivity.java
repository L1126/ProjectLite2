package com.projectlite2.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.projectlite2.android.R;
import com.projectlite2.android.activity.GuideActivity;
import com.projectlite2.android.activity.LoginActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.UserInfoSaveSharedPreference;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        //getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_splash);
        Thread myThread=new Thread(){//创建子线程
            @Override
            public void run() {
                try{
                    sleep(1000);//使程序休眠五秒
                    Intent it;
                    if (!UserInfoSaveSharedPreference.getPrefNotFirstUse(MyApplication.getContext())){
                         it=new Intent(getApplicationContext(), GuideActivity.class);//启动GuideActivity
                    }else{
                         //it=new Intent(getApplicationContext(), LoginActivity.class);//启动LoginActivity
                         it = new Intent(MyApplication.getContext(), MainActivity.class);

                    }
                    startActivity(it);
                    finish();//关闭当前活动
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }
}

