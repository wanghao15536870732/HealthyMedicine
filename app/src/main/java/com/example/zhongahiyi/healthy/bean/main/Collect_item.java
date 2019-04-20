package com.example.zhongahiyi.healthy.bean.main;

public class Collect_item {
    private String name;
    private String url;
    private int image;
    private String sourse;
    private String time;

    public Collect_item(String name, String url, String sourse, String time) {
        this.name = name;
        this.url = url;
        this.sourse = sourse;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSourse() {
        return sourse;
    }

    public void setSourse(String sourse) {
        this.sourse = sourse;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}