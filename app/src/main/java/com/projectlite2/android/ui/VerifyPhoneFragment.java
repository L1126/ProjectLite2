package com.projectlite2.android.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.databinding.FragmentVerifyPhoneBinding;
import com.projectlite2.android.viewmodel.VerifyPhoneViewModel;

import cn.leancloud.sms.AVSMS;
import cn.leancloud.sms.AVSMSOption;
import cn.leancloud.types.AVNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static androidx.navigation.Navigation.findNavController;

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

                VerifySMSCode(v, phoneNum, codeNum);


            }
        });

        // 点击获取验证码
        binding.btnGetCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String phoneNum=binding.txtPhone.getText().toString();
                if (phoneNum.equals("")){
                    MyApplication.ToastyWarning("请输入合法的手机号");
                    return;
                }


                SendSMSCode(phoneNum);
            }
        });


      return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 接收参数
        _jumpTo = getArguments().getString("JUMP_TO");
        //    MyApplication.showToast(_jumpTo);
    }



    public void VerifySMSCode(View v, String phoneNum, String codeNum) {
        AVSMS.verifySMSCodeInBackground(codeNum,phoneNum).subscribe(new Observer<AVNull>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(AVNull avNull) {
                MyApplication.ToastySuccess("success");
                Log.d("TAG","Result: Successfully verified the number.");

                // 跳转到注册-设置密码
                if (_jumpTo.equals("SIGN_UP")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("PHONE_NUMBER",phoneNum);
                    bundle.putString("SMS_CODE",codeNum);
                    MyApplication.navJump(v,R.id.action_varifyPhoneFragment_to_setPwdFragment,bundle);
                }
                // 跳转到忘记密码-修改密码
                else if (_jumpTo.equals("MODIFY_PASSWORD")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("PHONE_NUMBER",phoneNum);
                    bundle.putString("SMS_CODE",codeNum);
                    MyApplication.navJump(v,R.id.action_varifyPhoneFragment_to_modifyPwdFragment,bundle);
                } else {
                    MyApplication.showToast("传参失败");
                }


            }
            @Override
            public void onError(Throwable throwable) {
                MyApplication.ToastyError("error");
                Log.d("TAG","Result: Failed to verify the number. Reason: " + throwable.getMessage());
                return;
            }
            @Override
            public void onComplete() {
            }
        });
    }

    public void SendSMSCode(String phoneNum) {
        AVSMSOption option = new AVSMSOption();
        option.setTtl(10);
        option.setApplicationName("ProjectLite");
        option.setOperation("注册");
        AVSMS.requestSMSCodeInBackground(phoneNum, option).subscribe(new Observer<AVNull>() {

            @Override
            public void onSubscribe(Disposable disposable) {
            }
            @Override
            public void onNext(AVNull avNull) {
                MyApplication.ToastyInfo(phoneNum);
                MyApplication.ToastySuccess("success");
                Log.d("TAG","Result: Successfully sent verification code.");
            }
            @Override
            public void onError(Throwable throwable) {
                MyApplication.ToastyInfo(phoneNum);
                MyApplication.ToastyError("error");
                Log.d("TAG","Result: Failed to send verification code. Reason: " + throwable.getMessage());
            }
            @Override
            public void onComplete() {
            }
        });
    }




}