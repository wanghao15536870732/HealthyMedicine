package com.example.zhongahiyi.healthy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhongahiyi.healthy.R;

import java.util.Calendar;

import at.markushi.ui.CircleButton;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private static Context context;
    private EditText durgName, dose, remindTime, remark;
    private CalendarView beginTime, endTime;
    private CircleButton right, wrong;
    public int benginyear,benginmonth,benginday;
    public int endyear,endmonth,endday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        durgName = findViewById(R.id.setting_durg_edit);
        dose = findViewById(R.id.jiliang_edit);
        remindTime = findViewById(R.id.durg_remind_time_edit);
        remark = findViewById(R.id.durg_alart_beizhu_edit);
        beginTime = findViewById(R.id.durg_begin_time);
        endTime = findViewById(R.id.durg_end_time);
        right = findViewById(R.id.button_right1);
        wrong = findViewById(R.id.button_wrong1);
        setSupportActionBar(toolbar);
        right.setOnClickListener(this);
        wrong.setOnClickListener(this);
        beginTime.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(SettingActivity.this,
                        "您的开始是" + year + "年" + month + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                benginyear=year;
                benginmonth=month;
                benginday=dayOfMonth;
            }
        });
        endTime.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(SettingActivity.this,
                        "您的结束是" + year + "年" + month + "月" + dayOfMonth + "日", Toast.LENGTH_LONG).show();
                endyear=year;
                endmonth=month;
                endday=dayOfMonth;
            }
        });
    }

    private void getText() {
        String durgname = durgName.getText().toString();
        String durgDose = dose.getText().toString();
        String durgRemindTime = remindTime.getText().toString();
        String durgRemark = remindTime.getText().toString();
//        String durgBeginTime = ;
//        Sting durgEndTime = endTime.getDateTextAppearance();
        Log.e("TAG", durgname);
        Log.e("TAG", durgDose);
        Log.e("TAG", durgRemindTime);
        Log.e("TAG", durgRemark);
//        Log.e("TAG", String.valueOf(durgBeginTime));
//        Log.e("TAG", String.valueOf(durgEndTime));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_right1:
                getText();
                break;
            case R.id.button_wrong1:
                Intent intent1 = new Intent(SettingActivity.this, MainActivity.class);
                this.finish();
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
