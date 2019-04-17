package com.example.zhongahiyi.healthy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.bean.main.Collect_item;
import com.example.zhongahiyi.healthy.bean.main.History_item;

import java.util.List;

public class History_Recyle_Adapter extends RecyclerView.Adapter<History_Recyle_Adapter.ViewHolder>{

    private List<History_item> history_items;
    private Context context;

    public History_Recyle_Adapter(List<History_item> history_items) {
        this.history_items = history_items;
    }

    @NonNull
    @Override
    public History_Recyle_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_item,viewGroup,false);
        context = viewGroup.getContext();
        History_Recyle_Adapter.ViewHolder holder= new History_Recyle_Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull History_Recyle_Adapter.ViewHolder viewHolder, int i) {
        History_item history_item = history_items.get(i);
        Glide.with(context).load(history_item.getUrl()).into(viewHolder.mImageView);
        viewHolder.name.setText(history_item.getName());
        viewHolder.time.setText(history_item.getTime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"emmmmmmmm",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return history_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,time;
        private ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.history_name);
            time = (TextView)itemView.findViewById(R.id.history_time);
            mImageView = (ImageView) itemView.findViewById(R.id.history_image);
        }
    }
}
