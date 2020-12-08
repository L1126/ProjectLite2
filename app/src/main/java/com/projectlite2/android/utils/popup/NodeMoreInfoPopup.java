package com.projectlite2.android.utils.popup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.ProjectDetailActivity;
import com.projectlite2.android.app.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NodeMoreInfoPopup extends BottomPopupView {


    @BindView(R.id.btnReadMore)
    Button mBtnReadMore;


    Activity parentActivity;


    public NodeMoreInfoPopup(@NonNull Context context) {
        super(context);
        parentActivity= (Activity) context;
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.node_more_info_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);


    }

    // 最大高度为Window的0.85
    @Override
    protected int getMaxHeight() {

        return (int) (XPopupUtils.getAppHeight(getContext()) * .85f);
    }


    @OnClick({R.id.btnReadMore})
    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btnReadMore: {
                Intent intent = new Intent(MyApplication.getContext(), ProjectDetailActivity.class);
                getContext().startActivity(intent);
                break;
            }
        }

    }


}
