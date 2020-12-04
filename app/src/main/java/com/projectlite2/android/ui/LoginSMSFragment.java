package com.projectlite2.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.activity.MainActivity;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.R;
import com.projectlite2.android.databinding.FragmentLoginBinding;
import com.projectlite2.android.databinding.FragmentLoginSmsBinding;


import cn.leancloud.AVUser;
import cn.leancloud.sms.AVSMS;
import cn.leancloud.sms.AVSMSOption;
import cn.leancloud.types.AVNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static androidx.navigation.Navigation.findNavController;

;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginSMSFragment extends Fragment {


    public LoginSMSFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        FragmentLoginSmsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_sms, container, false);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());

        // 跳转-密码登录
        binding.linkPwdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = findNavController(v);
                controller.popBackStack();
            }
        });

        // 点击 获取验证码
        binding.btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum=binding.txtPhone.getText().toString();
                if (!MyApplication.isMobilePhoneNum(phoneNum)){
                    MyApplication.ToastyWarning("请输入合法的手机号");
                    return;
                }


                AVSMSOption option = new AVSMSOption();
                option.setSignatureName("ProjectLite"); //设置短信签名名称
                AVSMS.requestSMSCodeInBackground("+86"+phoneNum, option).subscribe(new Observer<AVNull>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }
                    @Override
                    public void onNext(AVNull avNull) {
                        Log.d("TAG","Result: succeed to request SMSCode.");
                        MyApplication.ToastyInfo("验证码已发送");
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("TAG","Result: failed to request SMSCode. cause:" + throwable.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });


            }
        });

        // 点击 登录
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum=binding.txtPhone.getText().toString();
                String codeNum=binding.txtSMSCode.getText().toString();

                if (phoneNum.equals("")){
                    MyApplication.ToastyWarning("请输入合法的手机号");
                    return;
                }
                if (codeNum.equals("")){
                    MyApplication.ToastyWarning("请输入验证码");
                    return;
                }

                SignUpOrLoginWithSMS(phoneNum,codeNum);

            }
        });

        // 跳转 注册新账户
        binding.linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.navJump(v,R.id.action_loginSMSFragment_to_varifyPhoneFragment);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * 使用手机短信验证码注册或登录
     * @param phoneNum 手机号码，不加86
     * @param codeNum   用户输入的验证码
     */
    public void  SignUpOrLoginWithSMS(String phoneNum,String codeNum){
        AVUser.signUpOrLoginByMobilePhoneInBackground("+86"+phoneNum, codeNum).subscribe(new Observer<AVUser>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(AVUser user) {
                // 登录成功
                MyApplication.ToastySuccess("登录成功");

                // 跳转到主页
                Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
                startActivity(intent);
                // 销毁LoginActivity
                getActivity().finish();

            }
            public void onError(Throwable throwable) {
                // 验证码不正确
                MyApplication.ToastyError("验证码错误");
            }
            public void onComplete() {}
        });
    }

}