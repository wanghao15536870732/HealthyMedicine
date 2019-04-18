package com.example.zhongahiyi.healthy.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.zhongahiyi.healthy.bean.news.Constant;
import com.example.zhongahiyi.healthy.bean.news.ClassBean;

public class NewsTabAdapter extends PagerAdapter {

    private ClassBean[] classes = Constant.getNewsClass();

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
