package com.projectlite2.android;

import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.projectlite2.android.databinding.FragmentLoginBinding;

import cn.leancloud.AVUser;
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

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.showToast(" 登陆成功");

                String phone=binding.txtPhone.getText().toString();
                String password=binding.txtPassword.getText().toString();
                AVUser.loginByEmail(phone,password ).subscribe(new Observer<AVUser>() {
                    public void onSubscribe(Disposable disposable) {
                    }

                    public void onNext(AVUser user) {
                        // 登录成功
                        MyApplication.showToast(phone+" "+password+" 登陆成功");
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
        binding.linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController=findNavController(v);
                navController.navigate(R.id.action_loginFragment2_to_signUpFragment);
            }
        });



        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}