package com.example.zhongahiyi.healthy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.adapter.InfoFragmentAdapter;
import com.example.zhongahiyi.healthy.view.adapter.InfoTabAdopter;
import com.example.zhongahiyi.healthy.view.adapter.Info_Recyle_Adapter;
import com.example.zhongahiyi.healthy.view.bean.info.Info_item;

import java.util.ArrayList;
import java.util.List;

public class FragmentInfo extends Fragment {

    private InfoTabAdopter minfoTabAdopter;
    private InfoFragmentAdapter minfoFragmentAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;



    public static FragmentInfo newInstance(String text) {
        FragmentInfo fragmentCommon = new FragmentInfo();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        minfoFragmentAdapter = new InfoFragmentAdapter(getFragmentManager());
        minfoTabAdopter = new InfoTabAdopter();
        mTabLayout = (TabLayout) view.findViewById(R.id.date_tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.medcine_page);
        mViewPager.setAdapter(minfoFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(minfoTabAdopter);

//        recyclerView = view.findViewById(R.id.rv_infoList);

        return view;
    }


}