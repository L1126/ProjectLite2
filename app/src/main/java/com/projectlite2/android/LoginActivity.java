package com.projectlite2.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;




public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        // 如果shp中存在保存的手机号码信息的话,自动登录
        if(UserInfoSaveSharedPreference.getUserPhone(MyApplication.getContext()).length() != 0)
        {
            // 跳转到主页
            Intent intent = new Intent(MyApplication.getContext(),MainActivity.class);
            startActivity(intent);
            // 销毁LoginActivity
            finish();
        }

    }
}