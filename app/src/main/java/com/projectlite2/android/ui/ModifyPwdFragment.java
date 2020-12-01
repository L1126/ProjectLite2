package com.projectlite2.android.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.databinding.FragmentModifyPwdBinding;

import cn.leancloud.AVUser;
import cn.leancloud.types.AVNull;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyPwdFragment extends Fragment {


    private String _phoneNumber;
    private String _smsCode;

    public ModifyPwdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        FragmentModifyPwdBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_modify_pwd, container, false);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());

        // 点击 修改密码
        binding.btnModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示手机号是否正确传递
                MyApplication.ToastyInfo(_phoneNumber);

                String password = binding.txtPwd.getText().toString();
                String confirm = binding.txtConfirmPwd.getText().toString();


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
                    ModifyPassword(_smsCode,password);
                }
            }
        });


        return binding.getRoot();
    }

    public void ModifyPassword(String smsCode,String password) {
        AVUser.resetPasswordBySmsCodeInBackground(smsCode, password).subscribe(new Observer<AVNull>() {
            public void onSubscribe(Disposable disposable) {}

            @Override
            public void onNext(@NonNull AVNull avNull) {
                //  成功
                MyApplication.ToastySuccess("密码修改成功");
            }

            public void onError(Throwable throwable) {
                // 验证码不正确
            }
            public void onComplete() {}
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //  接收参数
        _phoneNumber = getArguments().getString("PHONE_NUMBER");
        _smsCode=getArguments().getString("SMS_CODE");
    }


}