package com.example.zhongahiyi.healthy.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.bean.info.Info_item;

import java.util.List;

public class Info_Recyle_Adapter extends RecyclerView.Adapter<Info_Recyle_Holder> {

    private List<Info_item> info_items;
    private Context context;

    public Info_Recyle_Adapter(List<Info_item> info_items, Context context) {
        this.info_items = info_items;
        this.context = context;
    }

    @NonNull
    @Override
    public Info_Recyle_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_items,viewGroup,false);
        Info_Recyle_Holder info_recyle_holder = new Info_Recyle_Holder(view);
        return info_recyle_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Info_Recyle_Holder info_recyle_holder, int i) {
        info_recyle_holder.bindView(info_items.get(i));
    }

    @Override
    public int getItemCount() {
        return info_items.size();
    }
}
