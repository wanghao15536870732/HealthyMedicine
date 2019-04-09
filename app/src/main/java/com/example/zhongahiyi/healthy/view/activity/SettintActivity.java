package com.example.zhongahiyi.healthy.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhongahiyi.healthy.R;

public class SettintActivity extends AppCompatActivity {

    private ImageView setting_back;
    private ImageView setting_delete;
    private ImageView setting_save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_medcine);
        initdata();
    }

    public void initdata() {
        setting_delete = (ImageView) findViewById(R.id.delete_settings);
        setting_save = (ImageView) findViewById(R.id.save_settings);
        setting_back = (ImageView) findViewById(R.id.back_settings);
        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setting_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettintActivity.this);
                builder.setTitle("移除此项")
                        .setMessage("移除后将不再提醒")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        })
                        .show();

            }
        });
        setting_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettintActivity.this,"修改已保存",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
