package com.example.zhongahiyi.healthy.application;

import android.app.Application;

import com.example.zhongahiyi.healthy.utils.RecognitionManager;
import com.example.zhongahiyi.healthy.utils.SynthesisManager;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5cb08cee");
        RecognitionManager.getSingleton().init(this, "5cb08cee");
        SynthesisManager.getSingleton().init(this, "5cb08cee");
    }
}
