package com.example.zhongahiyi.healthy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.adapter.NewsListAdapter;
import com.example.zhongahiyi.healthy.bean.drug.NewsBean;
import com.example.zhongahiyi.healthy.bean.news.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<NewsBean> mNewsBeans = new ArrayList<>();
    private int classId;

    private NewsBean[][] news = {
            {new NewsBean(1, "飞华健康网", "2019年3月11日", "广东食药监局通报：39批次药品不合格", "",
                    "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG", "http://news.39.net/2019xhlt/190330/7010683.html")
                    , new NewsBean(2, "人民健康网", "2019年3月12日", "4+7带量医药行业要变天？看看一线业内人士怎么", "",
                    "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG", "http://news.39.net/2019xhlt/190330/7010683.html")
            }, {new NewsBean(3, "人民健康网", "2019年3月30日", "廉价“救命药”难买 原料药背锅？", "",
            "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG", "http://news.39.net/2019xhlt/190330/7010683.html")
            , new NewsBean(4, "39健康网", "2019年3月28日", "广东首部5G+4K远程手术“示教大片”开播", "",
            "https://pimg.39.net/PictureLib/A/f76/20190401/org_7675603.jpg", "http://zl.39.net/76/190401/7018754.html")
    }, {new NewsBean(5, "人民健康网", "2019年4月4日", "管好抗菌药 预防 “超级细菌”现身无药可用", "",
            "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG", "http://news.39.net/2019xhlt/190330/7010683.html")
    }, {new NewsBean(6, "飞华健康网", "2019年3月11日", "教育部、国家卫健委联合推进防控儿童青少年近视", "",
            "https://pimg.39.net/PictureLib/A/f76/20190330/org_7671517.JPG", "http://news.39.net/2019xhlt/190330/7010683.html")
            , new NewsBean(7, "飞华健康网", "2019/3/11", "广东食药监局通报：39批次药品不合格",
            "", "https://file.fh21static.com/fhfile1/M00/5C/71/ooYBAFoJDUuAJt4sAADOgAeOQtE16.jpeg", "https://news.fh21.com.cn/jksd/5259325.html")
    }, {new NewsBean(8, "人民健康网", "2019/4/4", "管好抗菌药 预防 “超级细菌”现身无药可用",
            "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555232670&di=67c8c026b24d98a5c80dbe8d7346f500&imgtype=jpg&er=1&src=http%3A%2F%2Ftxt6-2.book118.com%2F2017%2F0515%2Fbook47161%2F47160575.jpg",
            "http://health.people.com.cn/n1/2019/0407/c14739-31015901.html")
            , new NewsBean(9, "人民健康网", "2019/4/4", "教育部、国家卫健委联合推进防控儿童青少年近视", "",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554637978343&di=43c23bbdf2bffd453bbf4b3cbf59e25a&imgtype=0&src=http%3A%2F%2Fimg.shanda960.com%2Fsdg%2F20181030%2F20181030074231266.jpg",
            "http://health.people.com.cn/n1/2019/0404/c14739-31013328.html")
    }, {new NewsBean(10, "新浪医药网", "2019/4/2", "我国将对芬太尼类物质实施整类列管", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554637999289&di=10a8b6715179af419d537f8ee3bcde54&imgtype=0&src=http%3A%2F%2Fwww.lnszgh.org%2Fuploadfile%2F2%2FAttachment%2Fb66339e7f4.jpg",
            "http://health.people.com.cn/n1/2019/0402/c14739-31008252.html")
            , new NewsBean(11, "人民健康网", "2019/4/1", "三部门发布通告 5月1日起对芬太尼类物质实施整类列管", "",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554637999289&di=10a8b6715179af419d537f8ee3bcde54&imgtype=0&src=http%3A%2F%2Fwww.lnszgh.org%2Fuploadfile%2F2%2FAttachment%2Fb66339e7f4.jpg",
            "http://health.people.com.cn/n1/2019/0401/c14739-31006566.html")
            , new NewsBean(12, "新浪医药网", "2019/4/1", "2018年国家食品安全监督抽检样品平均不合格率为2.4%", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555232746&di=ea226ceef3b9a0d93d9276ca88e5ee05&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.gtimg.com%2Fln%2Fpics%2Fhv1%2F141%2F5%2F2251%2F146372691.jpg",
            "http://health.people.com.cn/n1/2019/0401/c14739-31006566.html")
    }, {new NewsBean(13, "人民健康网", "2019/4/1", "国家卫健委：婴幼儿照护服务机构设置标准尚在制定中", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555232784&di=c20cac4f7847ff084aab718cab742361&imgtype=jpg&er=1&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_ls%2F0%2F8359003064_294195%2F0",
            "http://health.people.com.cn/n1/2019/0331/c14739-31005002.html")
            , new NewsBean(14, "新浪医药网", "2019/3/31s", "新余卫健委回应正大天晴部分产品被取消销售资格：涉及药品…", "", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2941611158,1619904819&fm=26&gp=0.jpg",
            "http://health.people.com.cn/n1/2019/0329/c14739-31003272.html")
            , new NewsBean(15, "新浪医药网", "2019年4月1日", "市监总局：17批次食品不合格 涉及农兽药残留等指标", "", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1178309011,2817791450&fm=26&gp=0.jpg",
            "http://health.people.com.cn/n1/2019/0329/c14739-31002984.html")
    }, {new NewsBean(16, "人民健康网", "2019年4月2日", "医疗机构投诉实行“首诉负责制”", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554638352410&di=e73bfc0c37793d5b058794b3a941ba34&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Flarge%2F006K713uly1g1m95eexmlj30rk0fih51.jpg",
            "http://health.people.com.cn/n1/2019/0329/c14739-31002242.html")
            , new NewsBean(17, "新浪医药网", "2019年4月3日", "医疗废物处置将纳入中央环保督察", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555233101&di=4510986e52bcbaebed58d6738fba99e0&imgtype=jpg&er=1&src=http%3A%2F%2Fimg56.hbzhan.com%2F9%2F20141010%2F635485305208792058977.jpg",
            "http://health.people.com.cn/n1/2019/0329/c14739-31002177.html\n")
    }};


    private NewsListAdapter mNewsListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    ;

    public static NewsListFragment newInstance(int classId) {
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.ARGUMENT_CLASS_ID, classId);
        newsListFragment.setArguments(args);
        return newsListFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, null, false);
        //当前标题的id
        classId = getArguments().getInt(Constant.ARGUMENT_CLASS_ID);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_newsList);
        initData();
        mNewsListAdapter = new NewsListAdapter(mNewsBeans);
        mRecyclerView.setAdapter(mNewsListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.wrl_swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light);
        return view;
    }

    private void initData() {
        for (int i = 0; i < news[classId].length; i++) {
            mNewsBeans.add(news[classId][i]);
        }
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNewsBeans.clear();
                        for (int i = 0; i < 40; i++) {
                            Random random = new Random();
                            int index = random.nextInt(news[classId].length);
                            mNewsBeans.add(news[classId][index]);
                        }
                        mNewsListAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();
    }
}
