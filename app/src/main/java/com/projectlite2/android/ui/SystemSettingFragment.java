package com.projectlite2.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.LoginActivity;
import com.projectlite2.android.adapter.SysSettingAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.SysSettingCard;
import com.projectlite2.android.utils.OnItemClickListenerPlus;

import java.util.ArrayList;

import cn.leancloud.AVUser;

public class SystemSettingFragment extends Fragment {

    View mView;
    RecyclerView fRecyclerView, sRecyclerView, tRecyclerView;
    SysSettingAdapter fAdapter, sAdapter, tAdapter;
    TextView mLogout;

    public static Activity PARENT;

    private ArrayList<SysSettingCard> fSysSetList = new ArrayList<SysSettingCard>();
    private ArrayList<SysSettingCard> sSysSetList = new ArrayList<SysSettingCard>();
    private ArrayList<SysSettingCard> tSysSetList = new ArrayList<SysSettingCard>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_system_setting, container, false);

        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        initFirstSettingCard();
        initSecondSettingCard();
        initThirdSettingCard();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

        mLogout=mView.findViewById(R.id.myQuit);

        fAdapter.setOnKotlinItemClickListener(new OnItemClickListenerPlus() {
            @Override
            public void onClick(@org.jetbrains.annotations.Nullable View item, int position, int which) {
                switch (fSysSetList.get(position).getTextSys()){
                    case "账号安全":
                        NavController controller = Navigation.findNavController(mView);
                        controller.navigate(R.id.action_systemSettingFragment2_to_settingAccountFragment2);
                        break;
                    case "通用":
                        NavController controller1 = Navigation.findNavController(mView);
                        controller1.navigate(R.id.action_systemSettingFragment2_to_settingCurrencyFragment2);
                        break;
                    default:
                        break;
                }
            }
        });

        sAdapter.setOnKotlinItemClickListener(new OnItemClickListenerPlus() {
            @Override
            public void onClick(@org.jetbrains.annotations.Nullable View item, int position, int which) {
                switch (sSysSetList.get(position).getTextSys()){
                    case "消息通知":
                        NavController controller = Navigation.findNavController(mView);
                        controller.navigate(R.id.action_systemSettingFragment2_to_settingInfomationFragment2);
                        break;
                    case "隐私":
                        NavController controller1 = Navigation.findNavController(mView);
                        controller1.navigate(R.id.action_systemSettingFragment2_to_settingPrivacyFragment2);
                        break;
                    case "会议":
                        NavController controller2 = Navigation.findNavController(mView);
                        controller2.navigate(R.id.action_systemSettingFragment2_to_settingMeetingFragment2);
                        break;
                    default:
                        break;
                }
            }
        });

        tAdapter.setOnKotlinItemClickListener(new OnItemClickListenerPlus() {
            @Override
            public void onClick(@org.jetbrains.annotations.Nullable View item, int position, int which) {
                switch (tSysSetList.get(position).getTextSys()){
                    case "消息通知":
                        MyApplication.showToast(tSysSetList.get(position).getTextSys());
                        break;
                    default:
                        break;
                }
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new XPopup.Builder(getContext()).asConfirm("提示", "确定要退出当前账号吗？",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {

                                //  登出当前账号
                                AVUser.logOut();
                                //  回到登录界面
                                Intent it=new Intent(MyApplication.getContext(), LoginActivity.class);
                                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(it);


                            }
                        })
                        .show();

            }
        });

    }

    private void initFirstSettingCard(){
        fSysSetList.add(new SysSettingCard("账号安全","",true));
        fSysSetList.add(new SysSettingCard("通用","",true));
    }
    private void initSecondSettingCard(){
        sSysSetList.add(new SysSettingCard("消息通知","",true));
        sSysSetList.add(new SysSettingCard("隐私","",true));
        sSysSetList.add(new SysSettingCard("会议","",true));

    }
    private void initThirdSettingCard(){
        tSysSetList.add(new SysSettingCard("关于APP","",false));
    }
}
