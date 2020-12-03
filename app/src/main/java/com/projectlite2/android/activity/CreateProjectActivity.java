package com.projectlite2.android.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.projectlite2.android.R;
import com.projectlite2.android.app.MyApplication;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateProjectActivity extends AppCompatActivity
        implements
        DatePickerDialog.OnDateSetListener {

    DatePickerDialog datePickerDialog;
    int Year, Month, Day;

    @BindView(R.id.btnCancel)
    Button mBtnCancel;

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

    }

    @OnClick({R.id.btnCancel, R.id.btnCreate, R.id.btnSetStartDate, R.id.btnSetDeadline})
    void onClick(View v) {
        switch (v.getId()) {
            //  点击取消
            case R.id.btnCancel:
                finish();
                break;
            //  点击创建
            case R.id.btnCreate:
                new XPopup.Builder(this).asConfirm("提示", "确定创建该项目吗？",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                MyApplication.ToastyInfo("点击了确定");
                            }
                        })
                        .show();

                break;
            //  点击设置开始时间
            case R.id.btnSetStartDate:
                PopDatePickerDialog(getResources().getString(R.string.string_txt_btn_set_start_date));
                break;


            //  点击设置结束时间
            case R.id.btnSetDeadline:
                PopDatePickerDialog(getResources().getString(R.string.string_txt_btn_set_end_date));
                break;

            default:
                break;
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        MyApplication.ToastyInfo(date);
        datePickerDialog = null;
    }


    private void PopDatePickerDialog(String title) {
        datePickerDialog = DatePickerDialog.newInstance(CreateProjectActivity.this, Year, Month, Day);

        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(true);
        datePickerDialog.setTitle(title);

//                datePickerDialog.setAccentColor(R.color.purple_500);
//                datePickerDialog.setOkColor(R.color.purple_500);
//                datePickerDialog.setCancelColor(R.color.purple_500);

        datePickerDialog.setMinDate(Calendar.getInstance());
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                datePickerDialog=null;
            }
        });

        datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
    }

}

