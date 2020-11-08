package com.projectlite2.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.projectlite2.android.databinding.FragmentLoginBinding;

import cn.leancloud.AVUser;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static androidx.navigation.Navigation.findNavController;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LoginViewModel viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(getActivity());

        // 登录按钮
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=binding.txtPhone.getText().toString();
                String password=binding.txtPassword.getText().toString();
                AVUser.loginByMobilePhoneNumber(phone,password).subscribe(new Observer<AVUser>() {
                    public void onSubscribe(Disposable disposable) {
                    }

                    public void onNext(AVUser user) {
                        // 登录成功
                        MyApplication.showToast(phone+" "+password+" 登陆成功");
                        Intent intent = new Intent(MyApplication.getContext(),MainActivity.class);
                        startActivity(intent);
                        // 销毁LoginActivity
                        getActivity().finish();
                    }

                    public void onError(Throwable throwable) {
                        // 登录失败（可能是密码错误）
                        MyApplication.showToast(phone+" "+password+" 登陆失败");
                    }

                    public void onComplete() {
                    }
                });
            }
        });
        // 使用短信验证码登录-跳转
        binding.linkSMSLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavController navController=findNavController(v);
                navController.navigate(R.id.action_loginFragment2_to_loginSMSFragment);
            }
        });
        // 注册新账户-跳转-
        binding.linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController=findNavController(v);
                // 传递参数，跳转到注册
                Bundle bundle = new Bundle();
                bundle.putString("JUMP_TO", "SIGN_UP");
                navController.navigate(R.id.action_loginFragment2_to_varifyPhoneFragment,bundle);

            }
        });
        // 忘记密码-跳转
        binding.linkForgetpwd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavController navController=findNavController(v);
                // 传递参数，跳转到修改密码
                Bundle bundle = new Bundle();
                bundle.putString("JUMP_TO", "MODIFY_PASSWORD");
                navController.navigate(R.id.action_loginFragment2_to_varifyPhoneFragment,bundle);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}