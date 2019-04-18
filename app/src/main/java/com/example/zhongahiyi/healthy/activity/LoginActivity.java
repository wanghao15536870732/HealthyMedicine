package com.example.zhongahiyi.healthy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.zhongahiyi.healthy.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {

    private EditText editLoginUsername;
    private ImageView mIvLoginUsernameDel;
    private EditText editLoginPassword;
    private ImageView mIVLoginPwDel;


    private Button login;
    private Button register;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //username
        mIvLoginUsernameDel = findViewById(R.id.iv_login_username_del);
        editLoginUsername = findViewById(R.id.et_login_account);
        //password
        editLoginPassword = findViewById(R.id.et_login_password);
        mIVLoginPwDel = findViewById(R.id.iv_login_pw_del);

        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.login_register_hint);
        //注册点击事件
        mIvLoginUsernameDel.setOnClickListener(this);
        mIVLoginPwDel.setOnClickListener(this);
        editLoginUsername.setOnClickListener(this);
        editLoginPassword.setOnClickListener(this);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        //注册其他事件

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.et_login_account:
//                editLoginPassword.clearFocus();//点击editLoginPassword就改变焦点
//                editLoginUsername.setFocusableInTouchMode(true);
//                editLoginUsername.requestFocus();//把输入焦点放在调用这个方法的控件
//                break;
//            case R.id.et_login_password:
//                editLoginUsername.clearFocus();
//                editLoginPassword.setFocusableInTouchMode(true);
//                editLoginPassword.requestFocus();
//                break;
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                break;
            case R.id.login_register_hint:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.iv_login_username_del:
                //清空用户名
                editLoginUsername.setText(null);
                break;
            case R.id.iv_login_pw_del:
                //清空密码
                editLoginPassword.setText(null);
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    //用户名密码输入事件
    @Override
    public void afterTextChanged(Editable s) {
        String username = editLoginUsername.getText().toString().trim();
        String pwd = editLoginPassword.getText().toString().trim();

        //是否显示清除按钮
        if (username.length() > 0) {
            mIvLoginUsernameDel.setVisibility(View.VISIBLE);
        } else {
            mIvLoginUsernameDel.setVisibility(View.INVISIBLE);
        }
        if (pwd.length() > 0) {
            mIVLoginPwDel.setVisibility(View.VISIBLE);
        } else {
            mIVLoginPwDel.setVisibility(View.INVISIBLE);
        }

        //登录按钮是否可用
        if (!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(username)) {
            login.setBackgroundResource(R.drawable.submit);
            login.setTextColor(getResources().getColor(R.color.white));
        } else {
            login.setBackgroundResource(R.drawable.register_lock);
            login.setTextColor(getResources().getColor(R.color.account_lock_font_color));
        }
    }


}
