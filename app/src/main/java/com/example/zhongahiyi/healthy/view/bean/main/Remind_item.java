package com.example.zhongahiyi.healthy.view.bean.main;

public class Remind_item {
    private String name;
    private String content;
    private String url;
    private int image;

    public Remind_item(String name, String content, String url) {
        this.name = name;
        this.content = content;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
