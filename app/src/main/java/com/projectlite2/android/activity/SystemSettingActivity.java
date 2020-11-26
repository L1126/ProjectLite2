package com.projectlite2.android.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.projectlite2.android.R;
import com.projectlite2.android.adapter.MyProfileSettingItemAdapter;
import com.projectlite2.android.adapter.SysSettingAdapter;
import com.projectlite2.android.model.SysSettingCard;

import java.util.ArrayList;

public class SystemSettingActivity extends AppCompatActivity {

    View mView;
    RecyclerView fRecyclerView, sRecyclerView, tRecyclerView;
    SysSettingAdapter fAdapter, sAdapter, tAdapter;

    private ArrayList<SysSettingCard> fSysSetList = new ArrayList<SysSettingCard>();
    private ArrayList<SysSettingCard> sSysSetList = new ArrayList<SysSettingCard>();
    private ArrayList<SysSettingCard> tSysSetList = new ArrayList<SysSettingCard>();


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        mView = getWindow().getDecorView();

        initFirstSettingCard();
        initSecondSettingCard();
        initThirdSettingCard();
        StaggeredGridLayoutManager flayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager slayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager tlayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        fRecyclerView = mView.findViewById(R.id.firstSetting);
        fRecyclerView.setLayoutManager(flayoutManager);
        fAdapter = new SysSettingAdapter(fSysSetList);
        fRecyclerView.setAdapter(fAdapter);

        sRecyclerView = mView.findViewById(R.id.secondSetting);
        sRecyclerView.setLayoutManager(slayoutManager);
        sAdapter = new SysSettingAdapter(sSysSetList);
        sRecyclerView.setAdapter(sAdapter);

        tRecyclerView = mView.findViewById(R.id.thirdSetting);
        tRecyclerView.setLayoutManager(tlayoutManager);
        tAdapter = new SysSettingAdapter(tSysSetList);
        tRecyclerView.setAdapter(tAdapter);
    }

    private void initFirstSettingCard(){
        fSysSetList.add(new SysSettingCard("账号安全",""));
        fSysSetList.add(new SysSettingCard("通用",""));
    }
    private void initSecondSettingCard(){
        sSysSetList.add(new SysSettingCard("消息通知",""));
        sSysSetList.add(new SysSettingCard("隐私",""));
        sSysSetList.add(new SysSettingCard("会议",""));

    }
    private void initThirdSettingCard(){
        tSysSetList.add(new SysSettingCard("关于APP",""));
    }
}