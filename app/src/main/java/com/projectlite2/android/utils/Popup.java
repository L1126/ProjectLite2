package com.projectlite2.android.utils;

import android.content.Context;
import android.view.View;

import com.projectlite2.android.R;

import razerdp.basepopup.BasePopupWindow;

public class Popup extends BasePopupWindow {

    public Popup(Context context) {
        super(context);
    }

    // 必须实现，这里返回您的contentView
    // 为了让库更加准确的做出适配，强烈建议使用createPopupById()进行inflate
    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.pop_test);
    }

    @Override
    public View createPopupById(int layoutId) {
        return super.createPopupById(layoutId);
    }

}
