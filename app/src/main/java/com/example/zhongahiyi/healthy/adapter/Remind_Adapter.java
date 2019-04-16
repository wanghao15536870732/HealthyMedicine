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
import com.example.zhongahiyi.healthy.bean.main.Remind_item;

import java.util.List;

public class Remind_Adapter extends RecyclerView.Adapter<Remind_Adapter.ViewHolder> {
    private List<Remind_item> remind_items;

    public Remind_Adapter(List<Remind_item> remind_items) {
        this.remind_items = remind_items;
    }

    private Context context;
    @NonNull
    @Override
    public Remind_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.remind_item,viewGroup,false);
        context = viewGroup.getContext();
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Remind_Adapter.ViewHolder viewHolder, int i) {
        Remind_item remind_item = remind_items.get(i);
        Glide.with(context).load(remind_item.getUrl()).into(viewHolder.mImageView);
        viewHolder.content.setText(remind_item.getContent());
        viewHolder.title.setText(remind_item.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"emmmmmmmm",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return remind_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,content;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.remind_title);
            content = (TextView)itemView.findViewById(R.id.remind_content);
            mImageView = (ImageView)itemView.findViewById(R.id.remind_image);
        }
    }
}
