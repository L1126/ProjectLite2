package com.projectlite2.android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectlite2.android.databinding.FragmentLoginBinding;
import com.projectlite2.android.databinding.FragmentLoginSmsBinding;

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
        binding.linkPwdLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        // 点击 获取验证码
        binding.btnGetCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        // 点击 登录
        binding.btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        // 跳转 注册新账户
        binding.linkSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}