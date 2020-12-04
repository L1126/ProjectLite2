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

import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.databinding.FragmentVerifyPhoneBinding;

import org.jetbrains.annotations.NotNull;

import cn.leancloud.AVUser;
import cn.leancloud.sms.AVSMS;
import cn.leancloud.sms.AVSMSOption;
import cn.leancloud.types.AVNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyPhoneFragment extends Fragment {

    private String _jumpTo;

    public VerifyPhoneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        FragmentVerifyPhoneBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_phone, container, false);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNum = binding.txtPhone.getText().toString();
                String codeNum = binding.txtSMSCode.getText().toString();

                if (!MyApplication.isMobilePhoneNum(phoneNum)) {
                    MyApplication.ToastyWarning("请输入合法的手机号");
                    return;
                }
                if (codeNum.equals("")) {
                    MyApplication.ToastyWarning("请输入验证码");
                    return;
                }

                VerifySMSCode(v, phoneNum, codeNum,_jumpTo);


            }
        });

        // 点击获取验证码
        binding.btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNum = binding.txtPhone.getText().toString();
                if (phoneNum.equals("")) {
                    MyApplication.ToastyWarning("请输入合法的手机号");
                    return;
                }


                SendSMSCode(phoneNum,_jumpTo);
            }
        });


        return binding.getRoot();
    }

    /**
     * 接收bundle参数
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 接收参数
        _jumpTo = getArguments().getString("JUMP_TO");
        //    MyApplication.showToast(_jumpTo);
    }


    /**
     * 验证手机号码
     * @param v
     * @param phoneNum  手机号码，不加86
     * @param codeNum   用户输入的验证码
     * @param jumpPara  跳转参数，决定下一步的操作--设置密码/重置密码
     */
    public void VerifySMSCode(View v, String phoneNum, String codeNum, @NotNull String jumpPara) {

        switch (jumpPara) {
            case "SIGN_UP": {
                AVUser.signUpOrLoginByMobilePhoneInBackground("+86" + phoneNum, codeNum).subscribe(new Observer<AVUser>() {
                    public void onSubscribe(@NotNull Disposable disposable) {
                    }

                    public void onNext(@NotNull AVUser user) {
                        // 跳转到注册-设置密码

                        Bundle bundle = new Bundle();
                        bundle.putString("PHONE_NUMBER", phoneNum);
                        bundle.putString("SMS_CODE", codeNum);
                        MyApplication.navJump(v, R.id.action_varifyPhoneFragment_to_setPwdFragment, bundle);

                    }

                    public void onError(@NotNull Throwable throwable) {
                        // 验证码不正确
                        MyApplication.ToastyError("error");
                        Log.d("TAG", "Result: Failed to verify the number. Reason: " + throwable.getMessage());
                    }

                    public void onComplete() {
                    }
                });
            }
            break;
            case "MODIFY_PASSWORD": {
                // 跳转到忘记密码-修改密码
                Bundle bundle = new Bundle();
                bundle.putString("PHONE_NUMBER", phoneNum);
                bundle.putString("SMS_CODE", codeNum);
                MyApplication.navJump(v, R.id.action_varifyPhoneFragment_to_modifyPwdFragment, bundle);
            }
            break;
            default:
                MyApplication.ToastyInfo("method sendSMSCode error");
                break;
        }


    }


    /**
     * 发送短信验证码
     * @param phoneNum  手机号码，不加86
     * @param jumpPara  参数，决定发送短信类型为注册所用还是为修改密码所用
     */
    public void SendSMSCode(String phoneNum, @NotNull String jumpPara) {

        switch (jumpPara) {
            case "SIGN_UP": {
                AVSMSOption option = new AVSMSOption();
                option.setTtl(10);
                option.setApplicationName("ProjectLite");
                option.setOperation("注册");
                AVSMS.requestSMSCodeInBackground("+86" + phoneNum, option).subscribe(new Observer<AVNull>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(AVNull avNull) {
                        MyApplication.ToastyInfo(phoneNum);
                        MyApplication.ToastySuccess("success");
                        Log.d("TAG", "Result: Successfully sent verification code.");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        MyApplication.ToastyInfo(phoneNum);
                        MyApplication.ToastyError("error");
                        Log.d("TAG", "Result: Failed to send verification code. Reason: " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

            }

            break;
            case "MODIFY_PASSWORD": {

                AVUser.requestPasswordResetBySmsCodeInBackground("+86" + phoneNum).blockingSubscribe();

            }
            break;
            default:
                MyApplication.ToastyInfo("method sendSMSCode error");
                break;
        }


    }


}
