package com.projectlite2.android;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectlite2.android.databinding.FragmentLoginSmsBinding;
import com.projectlite2.android.databinding.FragmentModifyPwdBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyPwdFragment extends Fragment {

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

            }
        });

        return binding.getRoot();
    }
}