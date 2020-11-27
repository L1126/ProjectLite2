package com.projectlite2.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.projectlite2.android.R;
import com.projectlite2.android.adapter.SysSettingAdapter;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.model.SysSettingCard;
import com.projectlite2.android.utils.IKotlinItemClickListener;

import java.util.ArrayList;

public class SystemSettingFragment extends Fragment {

    View mView;
    RecyclerView fRecyclerView, sRecyclerView, tRecyclerView;
    SysSettingAdapter fAdapter, sAdapter, tAdapter;

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

        fAdapter.setOnKotlinItemClickListener(new IKotlinItemClickListener() {
            @Override
            public void onItemClickListener(int position){
//                MyApplication.showToast(fSysSetList.get(position).getTextSys());
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

        sAdapter.setOnKotlinItemClickListener(new IKotlinItemClickListener() {
            @Override
            public void onItemClickListener(int position){
//                MyApplication.showToast(sSysSetList.get(position).getTextSys());
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

        tAdapter.setOnKotlinItemClickListener(new IKotlinItemClickListener() {
            @Override
            public void onItemClickListener(int position){
                switch (tSysSetList.get(position).getTextSys()){
                    case "消息通知":
                        MyApplication.showToast(tSysSetList.get(position).getTextSys());
                        break;
                    default:
                        break;
                }
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
