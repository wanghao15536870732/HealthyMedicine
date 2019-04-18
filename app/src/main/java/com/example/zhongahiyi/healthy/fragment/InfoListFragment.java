package com.example.zhongahiyi.healthy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.adapter.Info_Recyle_Adapter;
import com.example.zhongahiyi.healthy.bean.info.Info_item;
import com.example.zhongahiyi.healthy.bean.news.Constant;

import java.util.ArrayList;
import java.util.List;



public class InfoListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Info_Recyle_Adapter info_recyle_adapter;
    private List<Info_item> info_items;

    private TextView textView;

    public static InfoListFragment newInstance(int dateId) {
        InfoListFragment infoListFragment = new InfoListFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.ARGUMENT_CLASS_ID, dateId);
        infoListFragment.setArguments(args);
        return infoListFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_list, null, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_infoList);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.info_RefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light);
        initData();
        info_recyle_adapter = new Info_Recyle_Adapter(info_items, getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(info_recyle_adapter);
        return view;
    }

    public void initData() {
        //FloatingActionMenu menu = (FloatingActionMenu)view.findViewById()
        info_items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            info_items.add(new Info_item(R.drawable.ic_pitch_off, "阿莫西林", "12.00", "1片"));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();


    }
}
