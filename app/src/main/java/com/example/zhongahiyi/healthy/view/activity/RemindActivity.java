package com.example.zhongahiyi.healthy.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.adapter.Remind_Adapter;
import com.example.zhongahiyi.healthy.view.bean.main.Remind_item;

import java.util.ArrayList;
import java.util.List;

public class RemindActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Remind_Adapter remind_adapter;
    private List<Remind_item> remind_items;
    private Context context;
    private ImageView remind_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remind_eat_medcine);
        recyclerView = (RecyclerView) findViewById(R.id.remind_recyle);
        initData();
        remind_adapter = new Remind_Adapter(remind_items);
        recyclerView.setAdapter(remind_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initData() {
        remind_back = (ImageView) findViewById(R.id.remind_back);
        remind_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        remind_items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            remind_items.add(new Remind_item("小儿复方氨酚烷胺片","本品为感冒用药类非处方药药品。",
                    "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2964785612,2678373046&fm=26&gp=0.jpg"));
        }
    }
}
