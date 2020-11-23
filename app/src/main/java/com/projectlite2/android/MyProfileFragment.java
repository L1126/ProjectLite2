package com.projectlite2.android;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MyProfileFragment extends Fragment {

    private MyProfileViewModel mViewModel;

    View mView;
    RecyclerView mRecyclerView;
    MyProfileSettingItemAdapter mAdapter;


    private ArrayList<MyProfileSettingItem> settingList = new ArrayList<MyProfileSettingItem>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mView=inflater.inflate(R.layout.my_profile_fragment, container, false);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);

     addSettings();
      StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
      mRecyclerView = mView.findViewById(R.id.settingsRecyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyProfileSettingItemAdapter(settingList);
    mRecyclerView.setAdapter(mAdapter);
    }


    /*
     * 初始化列表数据
     */
    private void addSettings() {
        settingList.add(new MyProfileSettingItem( "我的名片"));
        settingList.add(new MyProfileSettingItem( "设置"));
    }
}