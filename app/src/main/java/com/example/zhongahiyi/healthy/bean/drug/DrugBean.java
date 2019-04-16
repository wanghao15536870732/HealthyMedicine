package com.example.zhongahiyi.healthy.bean.drug;

import java.util.List;

public class DrugBean {
    private String title;
    private List<DrugItem> mList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DrugItem> getmList() {
        return mList;
    }

    public void setmList(List<DrugItem> mList) {
        this.mList = mList;
    }

}
