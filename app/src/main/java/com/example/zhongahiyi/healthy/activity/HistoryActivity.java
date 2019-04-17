package com.example.zhongahiyi.healthy.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.adapter.Collect_Recyle_Adapter;
import com.example.zhongahiyi.healthy.adapter.History_Recyle_Adapter;
import com.example.zhongahiyi.healthy.bean.main.Collect_item;
import com.example.zhongahiyi.healthy.bean.main.History_item;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private History_Recyle_Adapter history_recyle_adapter;
    private List<History_item> history_items;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_ill);
        recyclerView = (RecyclerView) findViewById(R.id.history_recyle);
        initData();
        history_recyle_adapter = new History_Recyle_Adapter(history_items);
        recyclerView.setAdapter(history_recyle_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initData() {
        history_items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            history_items.add(new History_item("慢性乙肝（活动性）","http://news.youth.cn/sh/201704/W020170418489230052873.jpg","2017.0322"));
        }
    }
}
