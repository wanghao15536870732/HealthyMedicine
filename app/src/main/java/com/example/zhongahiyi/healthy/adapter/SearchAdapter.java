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
import com.example.zhongahiyi.healthy.bean.main.Search_item;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Search_item> search_items;

    public SearchAdapter(List<Search_item> search_items) {
        this.search_items = search_items;
    }

    private Context context;

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        context = viewGroup.getContext();
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int i) {
        Search_item search_item = search_items.get(i);
        Glide.with(context).load(search_item.getUrl()).into(viewHolder.mImageView);
        viewHolder.title.setText(search_item.getName());
        viewHolder.hot_du.setText(search_item.getHot_du());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "emmmmmmmm", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return search_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,hot_du;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.search_title);
            mImageView = (ImageView) itemView.findViewById(R.id.search_image);
            hot_du = (TextView)itemView.findViewById(R.id.hot_du);
        }
    }
}
