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
import com.example.zhongahiyi.healthy.bean.main.Remind_item;

import java.util.List;

public class Collect_Recyle_Adapter extends RecyclerView.Adapter<Collect_Recyle_Adapter.ViewHolder> {

    private List<Collect_item> collect_items;
    private Context context;

    public Collect_Recyle_Adapter(List<Collect_item> collect_items) {
        this.collect_items = collect_items;
    }

    @NonNull
    @Override
    public Collect_Recyle_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_collect_item,viewGroup,false);
        context = viewGroup.getContext();
        Collect_Recyle_Adapter.ViewHolder holder= new Collect_Recyle_Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Collect_Recyle_Adapter.ViewHolder viewHolder, int i) {
        Collect_item collect_item = collect_items.get(i);
        Glide.with(context).load(collect_item.getUrl()).into(viewHolder.mImageView);
        viewHolder.source.setText(collect_item.getSourse());
        viewHolder.title.setText(collect_item.getName());
        viewHolder.time.setText(collect_item.getTime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"emmmmmmmm",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return collect_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,source,time;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tv_collect_title);
            source = (TextView)itemView.findViewById(R.id.tv_collect_source);
            time = (TextView)itemView.findViewById(R.id.tv_collect_time) ;
            mImageView = (ImageView)itemView.findViewById(R.id.img_collect);
        }
    }
}
