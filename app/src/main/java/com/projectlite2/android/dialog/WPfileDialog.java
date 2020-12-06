package com.projectlite2.android.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.projectlite2.android.R;

public class WPfileDialog extends CenterPopupView {

    private static EditText FileLoad;
    private  WPfileDialog thisDialog;

    public WPfileDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_file;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        thisDialog=this;
        FileLoad = findViewById(R.id.newFileLoad);

        findViewById(R.id.confirm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭弹窗
                dismiss();
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });

        findViewById(R.id.btnSelectfile).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WPloadDialog dialog = new WPloadDialog(getContext());
                dialog.isWPfiledialog = true;
                new XPopup.Builder(getContext())
                        .asCustom(dialog)
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

    public static void refreshFile(int txt){
        switch (txt){
            case 1:
                FileLoad.setText("调研小组");
                break;
            case 2:
                FileLoad.setText("原型小组");
                break;
            case 3:
                FileLoad.setText("代码小组");
                break;
            case 4:
                FileLoad.setText("文档小组");
                break;
            case 5:
                FileLoad.setText("公共小组");
                break;
            default:
                break;
        }
    }
}
