package com.projectlite2.android.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.projectlite2.android.utils.CloudUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateProjectActivity extends AppCompatActivity
        implements
        DatePickerDialog.OnDateSetListener {

    DatePickerDialog datePickerDialog;
    int Year, Month, Day;

    boolean isSetForStart;

    private String strSetStartDate;
    private String strSetEndDate;

    Date mDateStart;
    Date mDateClosing;

    @BindView(R.id.editProjectName)
    EditText mEditProjectName;

    @BindView(R.id.editProjectAbstract)
    EditText mEditProjectAbstract;

    @BindView(R.id.btnCreate)
    Button mBtnCreate;

    @BindView(R.id.btnSetStartDate)
    Button mBtnSetStartDate;

    @BindView(R.id.btnSetDeadline)
    Button mBtnSetDeadline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        getSupportActionBar().setTitle("Create project");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  mToolbar = findViewById(R.id.toolBar);

        ButterKnife.bind(this);

        strSetStartDate = getResources().getString(R.string.string_txt_btn_set_end_date);

        strSetEndDate = getResources().getString(R.string.string_txt_btn_set_end_date);

    }

    @OnClick({ R.id.btnCreate, R.id.btnSetStartDate, R.id.btnSetDeadline})
    void onClick(View v) {

        switch (v.getId()) {
            //  点击创建
            case R.id.btnCreate:

                if (mEditProjectName.getText().toString().equals("")) {
                    MyApplication.ToastyWarning("请输入项目名称");
                    break;
                }
                if (mDateStart == null) {
                    MyApplication.ToastyWarning("请设置项目开始日期");
                    break;
                }
                if (mDateClosing == null) {
                    MyApplication.ToastyWarning("请设置项目截止日期");
                    break;
                }



                new XPopup.Builder(this).asConfirm("提示", "确定创建该项目吗？",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {

                                String pjName = mEditProjectName.getText().toString();
                                String pjBrief = mEditProjectAbstract.getText().toString();
                                CloudUtil.CLASS_PROJECT.CreateProject(pjName, pjBrief, mDateStart, mDateClosing);
                                //  成功创建项目，向上个activity返回请求码，使其刷新
                                Intent it=new Intent();
                                it.putExtra("date_return","create success");
                                setResult(RESULT_OK, it);

                                //  关闭本活动
                                finish();

                            }
                        })
                        .show();

                break;
            //  点击设置开始时间
            case R.id.btnSetStartDate:
                isSetForStart = true;
                PopDatePickStart(strSetStartDate);
                break;


            //  点击设置结束时间
            case R.id.btnSetDeadline:
                isSetForStart = false;
                PopDatePickClosing(strSetEndDate);
                break;

            default:
                break;
        }
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


    private void PopDatePickStart(String title) {
        datePickerDialog = DatePickerDialog.newInstance(CreateProjectActivity.this, Year, Month, Day);

        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(true);
        datePickerDialog.setTitle(title);


        datePickerDialog.setMinDate(Calendar.getInstance());
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                datePickerDialog = null;
            }
        });

        datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
    }

    private void PopDatePickClosing(String title) {
        datePickerDialog = DatePickerDialog.newInstance(CreateProjectActivity.this, Year, Month, Day);

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

        datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
    }

}

