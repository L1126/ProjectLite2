package com.projectlite2.android.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joaquimley.faboptions.FabOptions;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.popup.AddMissionPopup;
import com.projectlite2.android.utils.popup.AddNodePopup;
import com.projectlite2.android.utils.popup.NodeMoreInfoPopup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.treeToolBar)
    Toolbar mToolbar;

    @BindView(R.id.txtPageTitle)
    TextView mPageTitle;


    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode0;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode1;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode2;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode3;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode4;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode5;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode6;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode7;

    @SuppressLint("StaticFieldLeak")
    public static FabOptions mNode8;

    @BindView(R.id.btnEdit)
    Button mBtnEdit;

    @BindView(R.id.btnExit)
    Button mBtnExit;

    @BindView(R.id.btnSave)
    Button mBtnSave;

    Boolean nodeWrap0, nodeWrap1, nodeWrap2, nodeWrap3, nodeWrap4, nodeWrap5, nodeWrap6, nodeWrap7, nodeWrap8;
    Boolean nodeShow0, nodeShow1, nodeShow2, nodeShow3, nodeShow4, nodeShow5, nodeShow6, nodeShow7, nodeShow8;
    Boolean editFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);


        ButterKnife.bind(this);
        initFabOnclick();

        mPageTitle.setText(R.string.string_txt_project_tree);


        mBtnEdit.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_options0:
                MyApplication.ToastyInfo("666");
                break;
            case R.id.btnAddSubNode:
                if (editFlag) {
                    new XPopup.Builder(this)
                            .asCustom(new AddNodePopup(this,this))
                            .show();
//                    AddNode();
                } else {
                    new XPopup.Builder(this).asConfirm("提示", "进入编辑模式才可以修改项目树哦！",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                }
                            })
                            .show();
                }
                break;
            case R.id.btnAddMission:
                if (editFlag) {
                    new XPopup.Builder(this)
                            .asCustom(new AddMissionPopup(this,this))
                            .show();
//                    AddNode();
                } else {
                    new XPopup.Builder(this).asConfirm("提示", "进入编辑模式才可以修改项目树哦！",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                }
                            })
                            .show();
                }
                break;

            case R.id.btnNodeMoreInfo:

                new XPopup.Builder(this)
                        .asCustom(new NodeMoreInfoPopup(this))
                        .show();

                break;
            case R.id.btnDelNode:

                break;
            case R.id.btnEdit: {

                editFlag = true;
                mBtnEdit.setVisibility(View.GONE);
                mBtnExit.setVisibility(View.VISIBLE);
                mBtnSave.setVisibility(View.VISIBLE);
                ToolbarChanged(editFlag);
                break;
            }
            case R.id.btnSave: {
                MyApplication.ToastySuccess("保存成功!");
                editFlag = false;
                mBtnSave.setVisibility(View.GONE);
                mBtnExit.setVisibility(View.GONE);
                mBtnEdit.setVisibility(View.VISIBLE);
                ToolbarChanged(editFlag);
                break;
            }
            case R.id.btnExit: {

                new XPopup.Builder(this).asConfirm("提示", "你还没保存，编辑改动的内容会消失哦，确定退出？",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                editFlag = false;
                                mBtnSave.setVisibility(View.GONE);
                                mBtnExit.setVisibility(View.GONE);
                                mBtnEdit.setVisibility(View.VISIBLE);
                                ToolbarChanged(editFlag);
                            }
                        })
                        .show();

                break;
            }
        }
    }


    public void initFabOnclick() {

        mNode0=findViewById(R.id.fab_options0);
        mNode1=findViewById(R.id.fab_options1);
        mNode2=findViewById(R.id.fab_options2);
        mNode3=findViewById(R.id.fab_options3);
        mNode4=findViewById(R.id.fab_options4);
        mNode5=findViewById(R.id.fab_options5);
        mNode6=findViewById(R.id.fab_options6);
        mNode7=findViewById(R.id.fab_options7);
        mNode8=findViewById(R.id.fab_options8);

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

    public static void AddNode() {
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

    public void ToolbarChanged(Boolean flag) {
        if (flag) {
            mPageTitle.setText(R.string.string_txt_edit_mode);
            mToolbar.setBackgroundResource(R.drawable.bg_red);
        } else {
            mPageTitle.setText(R.string.string_txt_project_tree);
            mToolbar.setBackgroundResource(R.color.colorAccent);
        }
    }


}