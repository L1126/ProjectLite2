package com.projectlite2.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.projectlite2.android.databinding.FragmentVerifyPhoneBinding;

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

                // 跳转到注册-设置密码
                if (_jumpTo.equals("SIGN_UP")) {
                    NavController navController = findNavController(v);
                    navController.navigate(R.id.action_varifyPhoneFragment_to_setPwdFragment);
                }
                // 跳转到忘记密码-修改密码
                else if (_jumpTo.equals("MODIFY_PASSWORD")) {
                    NavController navController = findNavController(v);
                    navController.navigate(R.id.action_varifyPhoneFragment_to_modifyPwdFragment);
                } else {
                    MyApplication.showToast("传参失败");
                }

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
}