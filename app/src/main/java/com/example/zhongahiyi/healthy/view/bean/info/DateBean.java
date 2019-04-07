package com.example.zhongahiyi.healthy.view.bean.info;

public class DateBean {
    private int id;
    private String date;

    public DateBean() {
    }

    public DateBean(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
