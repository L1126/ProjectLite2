package com.projectlite2.android.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;

public class WPfolderDialog extends CenterPopupView {

    public WPfolderDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_folder;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.confirm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });

        findViewById(R.id.btnSelect).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
                new XPopup.Builder(getContext())
                        .asCustom(new WPloadDialog(getContext()))
                        .show();
            }
        });
    }

    // 设置最大宽度
    @Override
    protected int getMaxWidth() {
        return super.getMaxWidth();
    }
    // 设置最大高度
    @Override
    protected int getMaxHeight() {
        return super.getMaxHeight();
    }

    /**
     * 弹窗的宽度，用来动态设定当前弹窗的宽度，受getMaxWidth()限制
     *
     * @return
     */
    protected int getPopupWidth() {
        return 0;
    }

    /**
     * 弹窗的高度，用来动态设定当前弹窗的高度，受getMaxHeight()限制
     *
     * @return
     */
    protected int getPopupHeight() {
        return 0;
    }
}
