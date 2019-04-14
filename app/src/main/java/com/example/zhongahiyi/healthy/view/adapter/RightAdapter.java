package com.example.zhongahiyi.healthy.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.activity.DrugDetailActivity;
import com.example.zhongahiyi.healthy.view.bean.drug.DrugItem;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder>{

    private List<DrugItem> mDrugItems;

    public RightAdapter(List<DrugItem> items){
        mDrugItems = items;
    }
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from( viewGroup.getContext())
                .inflate( R.layout.drug_item,viewGroup,false);
        context = viewGroup.getContext();
        final ViewHolder holder = new ViewHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final DrugItem drugItem = mDrugItems.get( i );
        Glide.with(context).load(drugItem.getUrl()).into(viewHolder.mImageView);
        viewHolder.title.setText( drugItem.getTitle() );
        viewHolder.content.setText( drugItem.getContent() );
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DrugDetailActivity.class);
                intent.putExtra(DrugDetailActivity.EXTRA_NAME,drugItem.getTitle());
                intent.putExtra(DrugDetailActivity.EXTRA_IMG,drugItem.getUrl());
                context.startActivity( intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDrugItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,content;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            title = (TextView) itemView.findViewById( R.id.drug_title );
            content = (TextView) itemView.findViewById( R.id.drug_info );
            mImageView = (ImageView) itemView.findViewById( R.id.drug_src);
        }
    }
}
