package com.example.zhongahiyi.healthy.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.zhongahiyi.healthy.view.bean.news.NewBean;
import com.example.zhongahiyi.healthy.view.bean.news.NewsBean;
import com.example.zhongahiyi.healthy.view.bean.news.Constant;

public class NewsTabAdapter extends PagerAdapter {

    private NewBean[] classes = Constant.getNewsClass();

    @Override
    public int getCount() {
        return classes.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return classes[position].getName();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
