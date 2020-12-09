package com.projectlite2.android.utils.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.CenterPopupView;
import com.projectlite2.android.R;
import com.projectlite2.android.activity.TreeActivity;
import com.projectlite2.android.app.MyApplication;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

public class AddMissionPopup extends CenterPopupView  implements
        DatePickerDialog.OnDateSetListener,View.OnClickListener {

    DatePickerDialog datePickerDialog;
    AppCompatActivity activity;
    TreeActivity parant;
    boolean isSetForStart;
    int Year, Month, Day;

    Date mDateStart;
    Date mDateClosing;

    Context ctx;

    Button mBtnSetStartDate;
    Button mBtnSetDeadline;
    Button mBtnAddNode;



    //注意：自定义弹窗本质是一个自定义View，但是只需重写一个参数的构造，其他的不要重写，所有的自定义弹窗都是这样。
    public AddMissionPopup(@NonNull Context context, TreeActivity p) {
        super(context);
        parant=p;
        activity= (AppCompatActivity) context;
        ctx=context;

    }
    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.add_mission_popup;
    }
    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        mBtnSetStartDate=findViewById(R.id.btnSetStartDate);
        mBtnSetDeadline=findViewById(R.id.btnSetDeadline);
        mBtnAddNode=findViewById(R.id.btnAddMission);
        mBtnSetStartDate.setOnClickListener(this);
        mBtnSetDeadline.setOnClickListener(this);
        mBtnAddNode.setOnClickListener(this);
    }
    // 设置最大宽度，看需要而定
    @Override
    protected int getMaxWidth() {
        return super.getMaxWidth();
    }
    // 设置最大高度，看需要而定
    @Override
    protected int getMaxHeight() {
        return super.getMaxHeight();
    }
    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
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




    private void PopDatePickStart(String title) {
        datePickerDialog = DatePickerDialog.newInstance(this, Year, Month, Day);

        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(true);
        datePickerDialog.setTitle(title);


        // datePickerDialog.setMinDate(Calendar.getInstance());
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                datePickerDialog = null;
            }
        });

        datePickerDialog.show(activity.getSupportFragmentManager(), "DatePickerDialog");
    }

    private void PopDatePickClosing(String title) {
        datePickerDialog = DatePickerDialog.newInstance(this, Year, Month, Day);

        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(true);
        datePickerDialog.setTitle(title);

        Calendar min = Calendar.getInstance();
        min.setTime(mDateStart);

        datePickerDialog.setMinDate(min);
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                datePickerDialog = null;
            }
        });

        datePickerDialog.show(activity.getSupportFragmentManager(), "DatePickerDialog");
    }


    //  设置日期返回
    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

//        String date = "You picked the following date: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
//        MyApplication.ToastyInfo(date);

        if (isSetForStart) {
            mDateStart = new Date(year - 1900, monthOfYear , dayOfMonth);
            mBtnSetStartDate.setText("" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
        } else {
            mDateClosing = new Date(year - 1900, monthOfYear, dayOfMonth);
            mBtnSetDeadline.setText("" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
        }

        Log.d("mytest", "isSetForStart " + isSetForStart + " onDateSet: pick success");

        datePickerDialog = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  点击设置开始时间
            case R.id.btnSetStartDate:
                isSetForStart = true;
                PopDatePickStart("节点开始时间");
                break;


            //  点击设置结束时间
            case R.id.btnSetDeadline:
                if(mDateStart!=null){
                    isSetForStart = false;
                    PopDatePickClosing("节点结束时间");
                }else{
                    MyApplication.ToastyWarning("请先设置开始时间");
                }

                break;

            case R.id.btnAddMission:
                MyApplication.ToastySuccess("任务编辑成功");
                this.dismiss();
        }
    }
}