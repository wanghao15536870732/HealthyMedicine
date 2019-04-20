package com.example.zhongahiyi.healthy.bean.main;

public class History_item {
    private String name;
    private String url;
    private int image;
    private String time;

    public History_item(String name, String url, String time) {
        this.name = name;
        this.url = url;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
