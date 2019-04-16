package com.example.zhongahiyi.healthy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhongahiyi.healthy.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button complete_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 测试
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        complete_register = findViewById(R.id.complete_register);
        complete_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.complete_register:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            default:
                break;
        }
    }

}