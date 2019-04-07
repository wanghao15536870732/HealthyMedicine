package com.example.zhongahiyi.healthy.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zhongahiyi.healthy.view.bean.news.NewBean;
import com.example.zhongahiyi.healthy.view.bean.news.NewsBean;
import com.example.zhongahiyi.healthy.view.bean.news.Constant;
import com.example.zhongahiyi.healthy.view.fragment.NewsListFragment;

public class NewsFragmentAdapter extends FragmentPagerAdapter {

    private NewBean[] classes = Constant.getNewsClass();

    public NewsFragmentAdapter(FragmentManager fm) {
        super( fm );
    }

    @Override
    public int getCount() {
        return classes.length;
    }

    @Override
    public Fragment getItem(int position) {
        NewBean clazz = classes[position];
        return NewsListFragment.newInstance( clazz.getId() );
    }
}
