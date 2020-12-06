package com.projectlite2.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lxj.xpopup.XPopup;
import com.projectlite2.android.R;
import com.projectlite2.android.dialog.WPfileDialog;
import com.projectlite2.android.adapter.WorkPlaceAdapter;
import com.projectlite2.android.dialog.WPfolderDialog;

public class WorkPlaceActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private WorkPlaceAdapter mWorkPlaceAdapter;

    private LinearLayout mLinearLayout;
    private View mView;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    private Activity mActivity;

    private FrameLayout uploadFrag, multiFrag, cfileFrag, cfolderFrag;

    private TextView restoreDefaults;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        getSupportActionBar().hide();//隐藏掉整个ActionBar
        setContentView(R.layout.activity_workplace);

        //初始化视图
        initViews();

        mLinearLayout = findViewById(R.id.toolBtnBar);
        mView = findViewById(R.id.view5);
        uploadFrag = findViewById(R.id.WPupload);
        multiFrag = findViewById(R.id.WPmulti);
        cfileFrag = findViewById(R.id.WPcfile);
        cfolderFrag = findViewById(R.id.WPcfolder);

        //上传
        uploadFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //多选
        multiFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //新建文件
        cfileFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(mActivity)
                        .asCustom(new WPfileDialog(mActivity))
                        .show();
            }
        });

        //新建文件夹
        cfolderFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(mActivity)
                        .asCustom(new WPfolderDialog(mActivity))
                        .show();
            }
        });

        //Tab点击事件
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mLinearLayout.setVisibility(View.VISIBLE);
                        mView.setVisibility(View.VISIBLE);
                        Log.d("KKang","第一个");
                        break;

                    case 1:
                        mLinearLayout.setVisibility(View.VISIBLE);
                        mView.setVisibility(View.VISIBLE);
                        Log.d("KKang","第二个");
                        break;

                    case 2:
                        mLinearLayout.setVisibility(View.GONE);
                        mView.setVisibility(View.GONE);
                        Log.d("KKang","第三个");
                        break;

                    case 3:
                        mLinearLayout.setVisibility(View.GONE);
                        mView.setVisibility(View.GONE);
                        Log.d("KKang","第四个");
                        break;

                }
            }
        });

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
