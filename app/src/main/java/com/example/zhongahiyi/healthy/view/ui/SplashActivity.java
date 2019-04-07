package com.example.zhongahiyi.healthy.view.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zhongahiyi.healthy.R;

public class SplashActivity extends AppCompatActivity{
    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.全屏主题
     * */
    //延时
    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView(){
        mhandler.sendEmptyMessageDelayed(1,2000);
    }
}
