package com.projectlite2.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.projectlite2.android.activity.ModifyMyProfileCardActivity;
import com.projectlite2.android.model.MyProfileSettingItem;
import com.projectlite2.android.viewmodel.MyProfileViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.SystemSettingActivity;
import com.projectlite2.android.adapter.MyProfileSettingItemAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.Constant;
import com.projectlite2.android.utils.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;


public class MyProfileFragment extends Fragment
{

    private MyProfileViewModel mViewModel;

    View mView;
    SwipeRecyclerView mRecyclerView;
    //    RecyclerView mRecyclerView;
    MyProfileSettingItemAdapter mAdapter;
    Toolbar toolBar;

    private ArrayList<MyProfileSettingItem> settingList = new ArrayList<MyProfileSettingItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.my_profile_fragment, container, false);
        toolBar = mView.findViewById(R.id.toolBar);




        return mView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
//
//        addSettings();
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView = mView.findViewById(R.id.settingsRecyclerView);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new MyProfileSettingItemAdapter(settingList);
//        mRecyclerView.setAdapter(mAdapter);
//
//
//        mAdapter.setOnKotlinItemClickListener(new IKotlinItemClickListener() {
//            @Override
//            public void onItemClickListener(int position) {
//                switch (position) {
//                    case Constant.MyProfileSettingItemPosition.MY_PROFILE_CARD:
//                        Intent modifyMyCardIntent = new Intent(MyApplication.getContext(), ModifyMyProfileCardActivity.class);
//                        startActivity(modifyMyCardIntent);
//                        break;
//                    case Constant.MyProfileSettingItemPosition.SYSTEM_SETTING:
//                        Intent systemSettingIntent = new Intent(MyApplication.getContext(), SystemSettingActivity.class);
//                        startActivity(systemSettingIntent);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//        addSettings();
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView = mView.findViewById(R.id.settingsRecyclerView);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new MyProfileSettingItemAdapter(settingList);
//        mRecyclerView.setAdapter(mAdapter);
//
//
//        mAdapter.setOnKotlinItemClickListener(new IKotlinItemClickListener() {
//            @Override
//            public void onItemClickListener(int position) {
//                switch (position) {
//                    case Constant.MyProfileSettingItemPosition.MY_PROFILE_CARD:
        mViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);

        addSettings();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView = mView.findViewById(R.id.settingsRecyclerView);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyProfileSettingItemAdapter(settingList);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnKotlinItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                switch (position) {
                    case Constant.MyProfileSettingItemPosition.MY_PROFILE_CARD:

                        Intent modifyMyCardIntent = new Intent(MyApplication.getContext(), ModifyMyProfileCardActivity.class);
                        startActivity(modifyMyCardIntent);
                        break;
                    case Constant.MyProfileSettingItemPosition.SYSTEM_SETTING:
                        Intent systemSettingIntent = new Intent(MyApplication.getContext(), SystemSettingActivity.class);

                        startActivity(systemSettingIntent);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private void addSettings() {
        settingList.add(new MyProfileSettingItem(R.drawable.ic_baseline_contact_mail_24, "我的名片"));
        settingList.add(new MyProfileSettingItem(R.drawable.ic_baseline_settings_24, "设置"));
    }



}