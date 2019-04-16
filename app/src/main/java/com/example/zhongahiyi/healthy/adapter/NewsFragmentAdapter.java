package com.example.zhongahiyi.healthy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zhongahiyi.healthy.bean.news.Constant;
import com.example.zhongahiyi.healthy.fragment.NewsListFragment;
import com.example.zhongahiyi.healthy.bean.news.ClassBean;

public class NewsFragmentAdapter extends FragmentPagerAdapter {

    private ClassBean[] classes = Constant.getNewsClass();

    public NewsFragmentAdapter(FragmentManager fm) {
        super( fm );
    }

    @Override
    public int getCount() {
        return classes.length;
    }

    @Override
    public Fragment getItem(int position) {
        ClassBean clazz = classes[position];
        return NewsListFragment.newInstance( clazz.getId() );
    }
}
