package com.projectlite2.android.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectlite2.android.LoginViewModel;
import com.projectlite2.android.R;
import com.projectlite2.android.databinding.FragmentModifyPwdBinding;
import com.projectlite2.android.databinding.FragmentSetPwdBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetPwdFragment extends Fragment {

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

            }
        });

        return binding.getRoot();
    }
}