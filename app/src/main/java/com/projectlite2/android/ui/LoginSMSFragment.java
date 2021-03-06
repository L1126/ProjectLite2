package com.projectlite2.android.ui;

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
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.R;
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
                if (phoneNum.equals("")){
                    MyApplication.ToastyWarning("请输入合法的手机号");
                    return;
                }

                AVUser.requestLoginSmsCodeInBackground(phoneNum).blockingSubscribe();
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

                AVUser.signUpOrLoginByMobilePhoneInBackground(phoneNum, codeNum).subscribe(new Observer<AVUser>() {
                    public void onSubscribe(Disposable disposable) {}
                    public void onNext(AVUser user) {
                        // 登录成功
                        MyApplication.ToastySuccess("success");
                    }
                    public void onError(Throwable throwable) {
                        // 验证码不正确
                        MyApplication.ToastyError("验证码错误");
                    }
                    public void onComplete() {}
                });

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


}