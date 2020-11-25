package com.projectlite2.android.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectlite2.android.viewmodel.MessageBoxViewModel;
import com.projectlite2.android.R;

public class MessageBoxFragment extends Fragment {

    private MessageBoxViewModel mViewModel;

    public static MessageBoxFragment newInstance() {
        return new MessageBoxFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_box_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MessageBoxViewModel.class);
        // TODO: Use the ViewModel
    }

}