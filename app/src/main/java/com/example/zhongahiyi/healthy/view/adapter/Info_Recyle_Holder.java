package com.example.zhongahiyi.healthy.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.bean.info.Info_item;


public class Info_Recyle_Holder extends RecyclerView.ViewHolder{

    private ImageView imageView;
    private TextView textView_name;
    private TextView textView_time;
    private TextView textView_count;

    public Info_Recyle_Holder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.pitch_m);
        textView_name = itemView.findViewById(R.id.medcine_name);
        textView_time = itemView.findViewById(R.id.eat_time);
        textView_count = itemView.findViewById(R.id.eat_count);
    }

    public void bindView(Info_item info_item){
        imageView.setImageResource(info_item.getImage());
        textView_name.setText(info_item.getName());
        textView_time.setText(info_item.getTime());
        textView_count.setText(info_item.getCount());
    }
}
