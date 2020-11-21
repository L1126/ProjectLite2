package com.projectlite2.android;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GuideThirdFragment extends Fragment {

    private Button btnFinishGuide;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_guide_third, container, false);
        btnFinishGuide =view.findViewById(R.id.btnFinishGuide);
        btnFinishGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.FIRST_USE=false;
                Intent it=new Intent(MyApplication.getContext(), MainActivity.class);//启动MainActivity
                startActivity(it);
                getActivity().finish();//关闭当前活动
            }
        });


        return view;
    }
}