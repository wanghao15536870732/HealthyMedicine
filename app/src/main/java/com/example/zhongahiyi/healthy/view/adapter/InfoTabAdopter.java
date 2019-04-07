package com.example.zhongahiyi.healthy.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.zhongahiyi.healthy.view.bean.info.DateBean;
import com.example.zhongahiyi.healthy.view.bean.info.DateConstant;

public class InfoTabAdopter extends PagerAdapter {
    private DateBean[] dateBeans = DateConstant.getInfoDate();

    @Override
    public int getCount() {
        return dateBeans.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return dateBeans[position].getDate();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }
}
