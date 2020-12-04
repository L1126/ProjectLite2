package com.projectlite2.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.projectlite2.android.R;
import com.projectlite2.android.adapter.WorkPlaceAdapter;
import com.projectlite2.android.ui.CardcaseFragment;
import com.projectlite2.android.ui.ContactListFragment;
import com.projectlite2.android.ui.WPFileListFragment;
import com.projectlite2.android.ui.WPListFragment;

import java.util.ArrayList;
import java.util.List;

public class WorkPlaceActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private WorkPlaceAdapter mWorkPlaceAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();//隐藏掉整个ActionBar
        setContentView(R.layout.activity_workplace);

        //初始化视图
        initViews();
    }

    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPagerWorkplace);
        mWorkPlaceAdapter = new WorkPlaceAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mWorkPlaceAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayoutWorkplace);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
    }

}
