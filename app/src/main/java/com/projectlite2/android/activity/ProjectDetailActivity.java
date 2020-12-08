package com.projectlite2.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projectlite2.android.R;

public class ProjectDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.string_txt_node_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_project_detail);
    }
}