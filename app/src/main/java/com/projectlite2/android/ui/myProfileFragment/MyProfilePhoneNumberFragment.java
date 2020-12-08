package com.projectlite2.android.ui.myProfileFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.projectlite2.android.R;
import com.projectlite2.android.activity.LoginActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.CloudUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyProfilePhoneNumberFragment extends Fragment {
    View mView;

    @BindView(R.id.btnModifyMyPhone)
    Button mBtnModifyMyPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_myprofile_phone_number, container, false);
        ButterKnife.bind(mView);

        return mView;
    }

//    @OnClick({R.id.btnModifyMyPhone})
//    private void OnClick(View v){
////        switch (v.getId()) {
//////            case R.id.btnModifyMyPhone:
//////                //  跳转修改手机号码界面
////////                Intent it=new Intent(MyApplication.getContext(), LoginActivity.class);
////////
////////                Bundle bundle = new Bundle();
////////
////////                startActivity(it);
////        }
//    }

}
