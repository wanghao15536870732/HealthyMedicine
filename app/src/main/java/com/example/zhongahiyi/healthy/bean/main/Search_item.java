package com.example.zhongahiyi.healthy.bean.main;

public class Search_item {
    private String name;
    private String url;
    private String hot_du;
    private int image;

    public Search_item(String name, String url, String hot_du) {
        this.name = name;
        this.url = url;
        this.hot_du = hot_du;
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

    public String getHot_du() {
        return hot_du;
    }

    public void setHot_du(String hot_du) {
        this.hot_du = hot_du;
    }
}
