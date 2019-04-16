package com.example.zhongahiyi.healthy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.bean.drug.NewsBean;
import com.example.zhongahiyi.healthy.utils.WebUtil;
//import WebUtil;

//import com.example.zhongahiyi.healthy.utils.WebUtil;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder>{

    private Context mContext;
    private List<NewsBean> mNewsItems;

    public NewsListAdapter(List<NewsBean> items){
        mNewsItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate( R.layout.item_news,viewGroup,false );
        final ViewHolder holder = new ViewHolder( view );
        mContext = viewGroup.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                NewsBean newsBean= mNewsItems.get( position );
                WebUtil.openWeb(mContext, newsBean.getTitle(), newsBean.getMain_url());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewsBean newsBean = mNewsItems.get( i );
        viewHolder.time.setText( newsBean.getTime() );
        viewHolder.author.setText( newsBean.getAuthor() );
        viewHolder.title.setText( newsBean.getTitle() );
        Glide.with(mContext ).load( newsBean.getUrl() ).into( viewHolder.mImageView );
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,content,time,author;
        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            title = (TextView) itemView.findViewById( R.id.tv_news_title );
            author = (TextView) itemView.findViewById( R.id.tv_news_source );
            time = (TextView) itemView.findViewById( R.id.tv_news_date );
            mImageView = (ImageView) itemView.findViewById( R.id.img_news);
        }
    }
}
