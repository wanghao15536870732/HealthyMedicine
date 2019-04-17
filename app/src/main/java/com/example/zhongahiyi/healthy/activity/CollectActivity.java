package com.example.zhongahiyi.healthy.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.adapter.Collect_Recyle_Adapter;
import com.example.zhongahiyi.healthy.adapter.Remind_Adapter;
import com.example.zhongahiyi.healthy.bean.main.Collect_item;
import com.example.zhongahiyi.healthy.bean.main.Remind_item;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Collect_Recyle_Adapter collect_recyle_adapter;
    private List<Collect_item> collect_items;
    private Context context;
    private ImageView collect_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        recyclerView = (RecyclerView) findViewById(R.id.collect_recyler);
        initData();
        collect_recyle_adapter = new Collect_Recyle_Adapter(collect_items);
        recyclerView.setAdapter(collect_recyle_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    private void initData() {
        collect_back = (ImageView) findViewById(R.id.collect_back);
        collect_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collect_items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collect_items.add(new Collect_item("广东食药监局通报：39批次药品不合格","https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG",
                    "人民健康网","2019年3月12日"));
        }
    }

}
