package com.example.zhongahiyi.healthy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zhongahiyi.healthy.bean.info.DateBean;
import com.example.zhongahiyi.healthy.bean.info.DateConstant;
import com.example.zhongahiyi.healthy.fragment.InfoListFragment;

public class InfoFragmentAdapter extends FragmentPagerAdapter{
    private DateBean[] dateBeans = DateConstant.getInfoDate();

    public InfoFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
       DateBean dateBean = dateBeans[i];
       return InfoListFragment.newInstance(dateBean.getId());
    }

    @Override
    public int getCount() {
        return dateBeans.length;
    }
}
