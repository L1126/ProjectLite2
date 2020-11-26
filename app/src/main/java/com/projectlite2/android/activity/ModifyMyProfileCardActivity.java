package com.projectlite2.android.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.projectlite2.android.R;
import com.projectlite2.android.adapter.SysSettingAdapter;
import com.projectlite2.android.model.SysSettingCard;

import java.util.ArrayList;

public class ModifyMyProfileCardActivity extends AppCompatActivity {

    View mView;
    RecyclerView infoRecyclerView, messRecyclerView;
    SysSettingAdapter infoAdapter, messAdapter;

    private ArrayList<SysSettingCard> infoSysSetList = new ArrayList<SysSettingCard>();
    private ArrayList<SysSettingCard> messSysSetList = new ArrayList<SysSettingCard>();

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_profile_card);
        mView = getWindow().getDecorView();

        initInfoSettingCard();
        initMessageSettingCard();
        StaggeredGridLayoutManager flayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager slayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        infoRecyclerView = mView.findViewById(R.id.infoSetting);
        infoRecyclerView.setLayoutManager(flayoutManager);
        infoAdapter = new SysSettingAdapter(infoSysSetList);
        infoRecyclerView.setAdapter(infoAdapter);

        messRecyclerView = mView.findViewById(R.id.messageSetting);
        messRecyclerView.setLayoutManager(slayoutManager);
        messAdapter = new SysSettingAdapter(messSysSetList);
        messRecyclerView.setAdapter(messAdapter);
    }

    private void initInfoSettingCard(){
        infoSysSetList.add(new SysSettingCard("姓名","小林"));
        infoSysSetList.add(new SysSettingCard("我的二维码",""));
    }
    private void initMessageSettingCard(){
        messSysSetList.add(new SysSettingCard("学校","华南理工大学"));
        messSysSetList.add(new SysSettingCard("年级","2018级"));
        messSysSetList.add(new SysSettingCard("专业","信息与交互设计"));
        messSysSetList.add(new SysSettingCard("擅长",""));
        messSysSetList.add(new SysSettingCard("优势",""));
        messSysSetList.add(new SysSettingCard("手机号","134 1717 4312"));
    }
}