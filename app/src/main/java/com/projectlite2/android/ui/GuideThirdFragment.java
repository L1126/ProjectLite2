package com.projectlite2.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.projectlite2.android.activity.LoginActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.R;
import com.projectlite2.android.utils.UserInfoSaveSharedPreference;


public class GuideThirdFragment extends Fragment {

    private Button btnFinishGuide;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guide_third, container, false);
        btnFinishGuide = view.findViewById(R.id.btnFinishGuide);
        btnFinishGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Thread myThread = new Thread() {//创建子线程
//                    @Override
//                    public void run() {
//                        try {
//
//                            Credential cred = new Credential(Constant.SECRET_ID, Constant.SECRET_KEY);
//
//                            HttpProfile httpProfile = new HttpProfile();
//                            httpProfile.setEndpoint("sms.tencentcloudapi.com");
//
//                            ClientProfile clientProfile = new ClientProfile();
//                            clientProfile.setHttpProfile(httpProfile);
//
//                            SmsClient client = new SmsClient(cred, "", clientProfile);
//
//                            SendSmsRequest req = new SendSmsRequest();
//                            String[] phoneNumberSet1 = {"+8613417174312"};
//                            req.setPhoneNumberSet(phoneNumberSet1);
//
//                            req.setSmsSdkAppid(Constant.APP_ID);
//                            req.setTemplateID(Constant.TEMPLATE_ID);
//                            req.setSign(Constant.SMS_SIGN);
//
//                            SendSmsResponse resp = client.SendSms(req);
//
//                            Log.d("SMS", SendSmsResponse.toJsonString(resp));
//
//                        } catch (TencentCloudSDKException e) {
//                            Log.d("SMS", e.toString());
//                        }
//                    }
//                };


                UserInfoSaveSharedPreference.setNotFirstUse(MyApplication.getContext(),true);
                Intent it=new Intent(MyApplication.getContext(), LoginActivity.class);//启动LoginActivity
                startActivity(it);
                getActivity().finish();//关闭当前活动
            }
        });


        return view;
    }
}