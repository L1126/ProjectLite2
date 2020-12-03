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
import com.projectlite2.android.model.SysSettingCard;
import com.projectlite2.android.utils.OnItemClickListenerPlus;

import java.util.ArrayList;

public class SystemMyProfileCardFragment extends Fragment {

    View mView;
    RecyclerView infoRecyclerView, messRecyclerView;
    SysSettingAdapter infoAdapter, messAdapter;

    private ArrayList<SysSettingCard> infoSysSetList = new ArrayList<SysSettingCard>();
    private ArrayList<SysSettingCard> messSysSetList = new ArrayList<SysSettingCard>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_modify_my_profile_card, container, false);

        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        initInfoSettingCard();
        initMessageSettingCard();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

        infoAdapter.setOnKotlinItemClickListener(new OnItemClickListenerPlus() {
            @Override
            public void onClick(@org.jetbrains.annotations.Nullable View item, int position, int which) {
                switch (infoSysSetList.get(position).getTextSys()) {
                    case "昵称":
                        NavController controller = Navigation.findNavController(mView);
                        controller.navigate(R.id.action_systemMyProfileCardFragment_to_myProfileNameFragment);
                        break;
                    case "我的二维码":
                        NavController controller1 = Navigation.findNavController(mView);
                        controller1.navigate(R.id.action_systemMyProfileCardFragment_to_myProfileQRCodeFragment);
                        break;
                    default:
                        break;
                }

            }
        });

        messAdapter.setOnKotlinItemClickListener(new OnItemClickListenerPlus() {
            @Override
            public void onClick(@org.jetbrains.annotations.Nullable View item, int position, int which) {
                switch (messSysSetList.get(position).getTextSys()){
                    case "学校":
                        NavController controller = Navigation.findNavController(mView);
                        controller.navigate(R.id.action_systemMyProfileCardFragment_to_myProfileSchoolFragment);
                        break;
                    case "年级":
                        NavController controller1 = Navigation.findNavController(mView);
                        controller1.navigate(R.id.action_systemMyProfileCardFragment_to_myProfileGradeFragment);
                        break;
                    case "专业":
                        NavController controller2 = Navigation.findNavController(mView);
                        controller2.navigate(R.id.action_systemMyProfileCardFragment_to_myProfileMajorFragment);
                        break;
                    case "优势":
                        NavController controller3 = Navigation.findNavController(mView);
                        controller3.navigate(R.id.action_systemMyProfileCardFragment_to_myProfileAdvantageFragment);
                        break;
                    case "手机号":
                        NavController controller4 = Navigation.findNavController(mView);
                        controller4.navigate(R.id.action_systemMyProfileCardFragment_to_myProfilePhoneNumberFragment);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initInfoSettingCard(){
        infoSysSetList.add(new SysSettingCard("昵称","小林",true));
        infoSysSetList.add(new SysSettingCard("性别","男", false));
        infoSysSetList.add(new SysSettingCard("我的二维码","",true));
    }
    private void initMessageSettingCard(){
        messSysSetList.add(new SysSettingCard("学校","华南理工大学",true));
        messSysSetList.add(new SysSettingCard("年级","大三",true));
        messSysSetList.add(new SysSettingCard("专业","信息与交互设计",true));
        messSysSetList.add(new SysSettingCard("优势","",true));
        messSysSetList.add(new SysSettingCard("手机号","134 1717 4312",true));
    }

}
