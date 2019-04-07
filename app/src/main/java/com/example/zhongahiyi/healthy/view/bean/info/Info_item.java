package com.example.zhongahiyi.healthy.view.bean.info;

public class Info_item {
    private int image;
    private String name;
    private String time;
    private String count;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Info_item(int image, String name, String time, String count) {
        this.image = image;

        this.name = name;
        this.time = time;
        this.count = count;
    }
}
