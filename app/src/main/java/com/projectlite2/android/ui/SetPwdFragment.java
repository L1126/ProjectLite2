package com.projectlite2.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.databinding.FragmentSetPwdBinding;

import cn.leancloud.AVObject;
import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetPwdFragment extends Fragment {

    private String _phoneNumber;
    private String _smsCode;

    public SetPwdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        FragmentSetPwdBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_pwd, container, false);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());


        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示手机号是否正确传递
                MyApplication.ToastyInfo(_phoneNumber);

                String password = binding.txtPwd.getText().toString();
                String confirm = binding.txtConfirmPwd.getText().toString();
                String userName = binding.txtUserName.getText().toString();

                if(userName.isEmpty()){
                    MyApplication.ToastyWarning("请输入用户名");
                    return;
                }
                if(password.isEmpty()){
                    MyApplication.ToastyWarning("请输入密码");
                    return;
                }
                if (confirm.isEmpty()){
                    MyApplication.ToastyWarning("请确认密码");
                    return;
                }

                //  如果两次密码输入不同则重新输入
                if (!password.equals(confirm)) {
                    MyApplication.ToastyWarning("两次密码输入不同，请检查输入。");
                    return;
                } else {


                    SetPassword(userName, password);


                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //  接收参数
        _phoneNumber = getArguments().getString("PHONE_NUMBER");
        _smsCode = getArguments().getString("SMS_CODE");
    }


    /**
     * 使用手机号码和密码注册
     *
     * @param userName 用户名
     * @param password 密码
     */
    public void SetPassword(String userName,  String password) {

        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            currentUser.setUsername(userName);
            currentUser.setPassword(password);
            currentUser.saveInBackground().subscribe(new Observer<AVObject>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }
                @Override
                public void onNext(@NonNull AVObject avObject) {
                    MyApplication.ToastySuccess("success");
                }
                @Override
                public void onError(@NonNull Throwable e) {
                    MyApplication.ToastyError("注册失败");
                }
                @Override
                public void onComplete() {
                }
            });

        } else {
            MyApplication.ToastyError("当前无用户");
        }

    }
}