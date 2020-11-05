package com.projectlite2.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.projectlite2.android.databinding.FragmentSignUpBinding;

import cn.leancloud.AVUser;
import cn.leancloud.sms.AVSMS;
import cn.leancloud.sms.AVSMSOption;
import cn.leancloud.types.AVNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SignUpViewModel viewModel = new ViewModelProvider(getActivity()).get(SignUpViewModel.class);
        FragmentSignUpBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());
        binding.btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String phone=binding.txtSignUpPhone.getText().toString();
//                String password=binding.txtSignUpPhone.getText().toString();

                AVSMSOption option = new AVSMSOption();
                option.setSignatureName("sign_name"); //设置短信签名名称
                AVSMS.requestSMSCodeInBackground("+8613417174312", option).subscribe(new Observer<AVNull>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(AVNull avNull) {
                        Log.d("TAG", "Result: succeed to request SMSCode.");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("TAG", "Result: failed to request SMSCode. cause:" + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

            }

        });
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser user = new AVUser();

// 等同于 user.put("username", "Tom")
                user.setUsername("Tom");
                user.setPassword("cat!@#123");

// 可选
                user.setEmail("tom@leancloud.rocks");
                user.setMobilePhoneNumber("+8618200008888");

// 设置其他属性的方法跟 AVObject 一样
                user.put("gender", "secret");

                user.signUpInBackground().subscribe(new Observer<AVUser>() {
                    public void onSubscribe(Disposable disposable) {
                    }

                    public void onNext(AVUser user) {
                        // 注册成功
                        System.out.println("注册成功。objectId：" + user.getObjectId());
                    }

                    public void onError(Throwable throwable) {
                        // 注册失败（通常是因为用户名已被使用）
                    }

                    public void onComplete() {
                    }
                });
            }

        });


        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}