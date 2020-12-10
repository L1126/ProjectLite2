package com.projectlite2.android.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.projectlite2.android.R;

public class WPloadDialog extends CenterPopupView {

    public static Boolean isWPfiledialog = false;
    public static Boolean isWPfloderdialog = false;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;

    public int _txt;

    public WPloadDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_load;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);

        findViewById(R.id.confirm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox1.isChecked()){
                    _txt = 1;
                }
                else if (checkBox2.isChecked()){
                    _txt = 2;
                }
                else if (checkBox3.isChecked()){
                    _txt = 3;
                }
                else if (checkBox4.isChecked()){
                    _txt = 4;
                }
                else if (checkBox5.isChecked()){
                    _txt = 5;
                }

                if(isWPfiledialog){
                    WPfileDialog.refreshFile(_txt);
                }
                else if(isWPfloderdialog){
                    WPfolderDialog.refreshFloder(_txt);
                }

                // 关闭弹窗
                dismiss();

                isWPfiledialog = false;
                isWPfloderdialog = false;
            }
        });

        findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗

                isWPfiledialog = false;
                isWPfloderdialog = false;
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
