package com.projectlite2.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.ui.SetPwdFragment;

import java.util.List;

import cn.leancloud.AVUser;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        //StatusBarUtil.setTransparent(LoginActivity.this);

        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            // 跳到首页
            // 跳转到主页
            Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
            startActivity(intent);
            // 销毁LoginActivity
            finish();
        } else {
            // 显示注册或登录页面
        }


    }


    /**
     * 解决Fragment中的onActivityResult()方法无响应问题。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 1.使用getSupportFragmentManager().getFragments()获取到当前Activity中添加的Fragment集合
         * 2.遍历Fragment集合，手动调用在当前Activity中的Fragment中的onActivityResult()方法。
         */

        Log.d("mytest", "onActivityResult: login activity");

        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment mFragment : fragments) {
                mFragment.onActivityResult(requestCode, resultCode, data);
            }
        }

    }


}
