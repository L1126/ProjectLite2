package com.projectlite2.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joaquimley.faboptions.FabOptions;
import com.projectlite2.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolBar)
    Toolbar mToolbar;

    @BindView(R.id.txtPageTitle)
    TextView mPageTitle;

    @BindView(R.id.fab_options0)
    FabOptions mNode0;

    @BindView(R.id.fab_options1)
    FabOptions mNode1;

    @BindView(R.id.fab_options2)
    FabOptions mNode2;

    @BindView(R.id.fab_options3)
    FabOptions mNode3;

    @BindView(R.id.fab_options4)
    FabOptions mNode4;

    @BindView(R.id.fab_options5)
    FabOptions mNode5;

    @BindView(R.id.fab_options6)
    FabOptions mNode6;

    @BindView(R.id.fab_options7)
    FabOptions mNode7;

    @BindView(R.id.fab_options8)
    FabOptions mNode8;

    Boolean nodeWrap0, nodeWrap1, nodeWrap2, nodeWrap3, nodeWrap4, nodeWrap5, nodeWrap6, nodeWrap7, nodeWrap8;
    Boolean nodeShow0, nodeShow1, nodeShow2, nodeShow3, nodeShow4, nodeShow5, nodeShow6, nodeShow7, nodeShow8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        initFabOnclick();

        mPageTitle.setText(R.string.string_txt_project_tree);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddSubNode:
                AddNode();
                break;
            case R.id.btnAddMission:

                break;
            case R.id.btnEditNode:

                break;
            case R.id.btnDelNode:

                break;
        }
    }


    public void initFabOnclick() {
        mNode0.setOnClickListener(this);
        mNode1.setOnClickListener(this);
        mNode2.setOnClickListener(this);
        mNode3.setOnClickListener(this);
        mNode4.setOnClickListener(this);
        mNode5.setOnClickListener(this);
        mNode6.setOnClickListener(this);
        mNode7.setOnClickListener(this);
        mNode8.setOnClickListener(this);
        nodeWrap0 = nodeWrap1 = nodeWrap2 = nodeWrap3 = nodeWrap4 = nodeWrap5 = nodeWrap6 = nodeWrap7 = nodeWrap8 = false;
        nodeShow0 = nodeShow1 = nodeShow2 = nodeShow3 = nodeShow4 = nodeShow5 = nodeShow6 = nodeShow7 = nodeShow8 = false;
        nodeShow0 = true;
    }

    public void AddNode() {
        if (mNode0.getVisibility() != View.GONE) {
            if (mNode1.getVisibility() != View.GONE) {
                if (mNode2.getVisibility() != View.GONE) {
                    if (mNode3.getVisibility() != View.GONE) {
                        if (mNode4.getVisibility() != View.GONE) {
                            if (mNode5.getVisibility() != View.GONE) {
                                if (mNode6.getVisibility() != View.GONE) {
                                    if (mNode7.getVisibility() != View.GONE) {
                                        if (mNode8.getVisibility() != View.GONE) {

                                        } else {
                                            mNode8.setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        mNode7.setVisibility(View.VISIBLE);
                                    }

                                } else {
                                    mNode6.setVisibility(View.VISIBLE);
                                }
                            } else {
                                mNode5.setVisibility(View.VISIBLE);
                            }
                        } else {
                            mNode4.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mNode3.setVisibility(View.VISIBLE);
                    }
                } else {
                    mNode2.setVisibility(View.VISIBLE);
                }
            } else {
                mNode1.setVisibility(View.VISIBLE);
            }
        } else {
            mNode0.setVisibility(View.VISIBLE);
        }
    }


}