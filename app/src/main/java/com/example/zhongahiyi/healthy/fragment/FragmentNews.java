package com.example.zhongahiyi.healthy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.adapter.NewsFragmentAdapter;
import com.example.zhongahiyi.healthy.adapter.NewsTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentNews extends Fragment {

    private NewsTabAdapter mNewsTabAdapter;
    private NewsFragmentAdapter mNewsFragmentAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<NewsListFragment> newsListFragments;

    public static FragmentNews newInstance(String text) {
        FragmentNews fragmentCommon = new FragmentNews();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mNewsFragmentAdapter = new NewsFragmentAdapter(getFragmentManager());
        mNewsTabAdapter = new NewsTabAdapter();
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        initData();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return newsListFragments.get(i);
            }

            @Override
            public int getCount() {
                return newsListFragments.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return super.getPageTitle(position);
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mNewsTabAdapter);
        return view;
    }

    private void initData() {
        newsListFragments = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            newsListFragments.add(NewsListFragment.newInstance(i));
        }
    }
}