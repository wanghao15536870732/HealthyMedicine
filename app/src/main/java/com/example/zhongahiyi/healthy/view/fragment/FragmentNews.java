package com.example.zhongahiyi.healthy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.adapter.NewsFragmentAdapter;
import com.example.zhongahiyi.healthy.view.adapter.NewsTabAdapter;

public class FragmentNews extends Fragment {

    private NewsTabAdapter mNewsTabAdapter;
    private NewsFragmentAdapter mNewsFragmentAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static FragmentNews newInstance(String text){
        FragmentNews fragmentCommon = new FragmentNews();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_news,container,false);
        mNewsFragmentAdapter = new NewsFragmentAdapter( getFragmentManager());
        mNewsTabAdapter = new NewsTabAdapter();
        mTabLayout = (TabLayout) view.findViewById( R.id.tabLayout );
        mViewPager = (ViewPager) view.findViewById( R.id.viewPager );
        mViewPager.setAdapter( mNewsFragmentAdapter );
        mTabLayout.setupWithViewPager( mViewPager );
        mTabLayout.setTabsFromPagerAdapter( mNewsTabAdapter );
        return view;
    }
}