package com.example.zhongahiyi.healthy.view.activity;

import android.app.admin.DeviceAdminService;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.db.DBManager;
import com.github.nukc.amountview.AmountView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import at.markushi.ui.CircleButton;

public class DataSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private AmountView dayamountView;
    private AmountView jiliangamountView;
    private int time = 1, dosage = 1;
    private CircleButton right, again, wrong;
    private final int REQUEST_CODE = 1;
    private TextView drugDetail;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_select);
        Toolbar toolbar = findViewById(R.id.toolbar);
        right = findViewById(R.id.button_right);
        again = findViewById(R.id.button_again);
        wrong = findViewById(R.id.button_wrong);
        drugDetail = findViewById(R.id.drug_detail);
        imageView = (ImageView) findViewById(R.id.drug_content_s);
        Glide.with(DataSelectActivity.this).load("http://ppwkqlsve.bkt.clouddn.com/drug1.jpg").into(imageView);
        right.setOnClickListener(this);
        wrong.setOnClickListener(this);
        again.setOnClickListener(this);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        showData(data);
        initAmountview();
    }

    private void initAmountview() {
        dayamountView = findViewById(R.id.drug_day_count);
        jiliangamountView = findViewById(R.id.drug_jiliang);
        dayamountView.setGoods_storage(999);
        jiliangamountView.setGoods_storage(999);
        jiliangamountView.setListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                dosage = amount;
//                Log.d("ok",String.valueOf(dosage));
            }
        });
        dayamountView.setListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                time = amount;
//                Log.d("ok",String.valueOf(time));
            }
        });
    }

    private void loadData() {


    }

    public void showData(String data) {
        switch (data) {
            case "6902401002635":

                break;
            default:
                break;

        }
    }

    private void changeDrugDetail(String data) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == -1) {
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Intent intent = new Intent(DataSelectActivity.this, DataSelectActivity.class);
                        intent.putExtra("data", result);
                        this.finish();
                        startActivity(intent);
//                        Toast.makeText(MainActivity.this, "阿莫西林胶囊已添加到您的药单", Toast.LENGTH_LONG).show();
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(DataSelectActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_again:
                Intent intent = new Intent(DataSelectActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.button_right:
                DBManager dbManager = new DBManager(DataSelectActivity.this);
                SQLiteDatabase db1 = dbManager.getDatabase();
                Cursor cursor = db1.query("durgclass", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String res = cursor.getString(cursor.getColumnIndex("count"));
                        Log.e("TAG", res);
                    } while (cursor.moveToNext());
                }
                Toast.makeText(this, "数据库查询成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_wrong:
                Intent intent1 = new Intent(DataSelectActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }


}
