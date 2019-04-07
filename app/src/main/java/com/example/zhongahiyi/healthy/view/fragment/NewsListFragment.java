package com.example.zhongahiyi.healthy.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.adapter.NewsListAdapter;
import com.example.zhongahiyi.healthy.view.bean.news.Constant;
import com.example.zhongahiyi.healthy.view.bean.news.NewsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    private List<NewsBean> mNewsBeans = new ArrayList<>();

    private NewsBean[] news = {
            new NewsBean( 1,"飞华健康网","2019年3月11日","广东食药监局通报：39批次药品不合格", "",
                    "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG","http://news.39.net/2019xhlt/190330/7010683.html")
            ,new NewsBean( 2,"人民健康网","2019年3月12日","4+7带量医药行业要变天？看看一线业内人士怎么", "",
            "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG","http://news.39.net/2019xhlt/190330/7010683.html")
            ,new NewsBean( 1,"人民健康网","2019年3月30日","廉价“救命药”难买 原料药背锅？", "",
            "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG","http://news.39.net/2019xhlt/190330/7010683.html")
            ,new NewsBean( 1,"39健康网","2019年3月28日","广东首部5G+4K远程手术“示教大片”开播", "",
            "https://pimg.39.net/PictureLib/A/f76/20190401/org_7675603.jpg","http://zl.39.net/76/190401/7018754.html")
            ,new NewsBean( 1,"人民健康网","2019年4月4日","管好抗菌药 预防 “超级细菌”现身无药可用", "",
            "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG","http://news.39.net/2019xhlt/190330/7010683.html")
            ,new NewsBean( 1,"飞华健康网","2019年3月11日","教育部、国家卫健委联合推进防控儿童青少年近视", "",
            "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG","http://news.39.net/2019xhlt/190330/7010683.html")
    };


    private NewsListAdapter mNewsListAdapter;
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
        initData();
        mNewsListAdapter = new NewsListAdapter(mNewsBeans);
        mRecyclerView.setAdapter( mNewsListAdapter );
        mRecyclerView.setLayoutManager( new LinearLayoutManager( container.getContext() ) );
        mRecyclerView.addItemDecoration( new DividerItemDecoration( container.getContext(),DividerItemDecoration.VERTICAL ) );
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById( R.id.wrl_swipeRefreshLayout );
        mSwipeRefreshLayout.setOnRefreshListener( this );
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light );
        return view;
    }

    private void initData(){
        for (int i = 1; i < 5; i++) {
            mNewsBeans.add( news[i] );
        }
    }

    @Override
    public void onRefresh(){
        new Thread( new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep( 2000 );
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        mNewsBeans.clear();
                        for (int i = 0; i < 40; i++) {
                            Random random = new Random(  );
                            int index = random.nextInt(news.length);
                            mNewsBeans.add( news[index] );
                        }
                        mNewsListAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing( false );
                    }
                } );

            }
        } ).start();
    }
}
