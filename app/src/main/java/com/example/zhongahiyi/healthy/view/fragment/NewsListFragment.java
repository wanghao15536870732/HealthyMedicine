package com.example.zhongahiyi.healthy.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.bean.news.Constant;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;;

    private SwipeRefreshLayout mSwipeRefreshLayout;;
    public static NewsListFragment newInstance(int classId){
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt( Constant.ARGUMENT_CLASS_ID, classId);
        newsListFragment.setArguments(args);
        return newsListFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_news_list, null, false );
        mRecyclerView = (RecyclerView) view.findViewById( R.id.rv_newsList );
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById( R.id.wrl_swipeRefreshLayout );
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light );
        return view;
    }

    @Override
    public void onRefresh() {

    }
}
