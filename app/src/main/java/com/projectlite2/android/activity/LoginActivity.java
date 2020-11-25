package com.projectlite2.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.R;
import com.projectlite2.android.utils.UserInfoSaveSharedPreference;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        String prefUserPhoneNumber= UserInfoSaveSharedPreference.getUserPhone(MyApplication.getContext());
        Log.d("PREF", "onCreate: savedPhoneNumber? "+prefUserPhoneNumber);
        // 如果shp中存在保存的手机号码信息的话,自动登录
        if(!prefUserPhoneNumber.equals(UserInfoSaveSharedPreference.PREF_NULL_VALUE))
        {
            // 跳转到主页
            Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
            startActivity(intent);
            // 销毁LoginActivity
            finish();
        }

    }
}