package com.example.zhongahiyi.healthy.view.bean.news;

public class Constant {
    public static final String ARGUMENT_CLASS_ID = "argument_class_id";

    public static final NewBean[] getNewsClass(){
        NewBean[] classes = new NewBean[7];
        classes[0] = new NewBean(1,"企业要闻");
        classes[1] = new NewBean(2,"医疗新闻");
        classes[2] = new NewBean(3,"生活贴士");
        classes[3] = new NewBean(4,"药品新闻");
        classes[4] = new NewBean(5,"疾病快讯");
        classes[5] = new NewBean(6,"食品新闻");
        classes[6] = new NewBean(7,"社会热点");
        return classes;
    }
}
