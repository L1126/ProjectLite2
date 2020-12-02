package com.projectlite2.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.projectlite2.android.R;

public class CreateProjectActivity extends AppCompatActivity {

    //Toolbar mToolbar;
    ImageButton mBtnSetTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Create project");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  mToolbar = findViewById(R.id.toolBar);
        mBtnSetTime = findViewById(R.id.btnSetTime);
//
//        mBtnSetTime.setOnClickListener(this);

        setContentView(R.layout.activity_create_project);
//
//        mBtnSetTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

}
