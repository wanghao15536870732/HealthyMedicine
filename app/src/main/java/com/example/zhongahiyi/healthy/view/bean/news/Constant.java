package com.example.zhongahiyi.healthy.view.bean.news;

public class Constant {
    public static final String ARGUMENT_CLASS_ID = "argument_class_id";

    public static final ClassBean[] getNewsClass(){
        ClassBean[] classes = new ClassBean[8];
        classes[0] = new ClassBean(0,"智能推荐");
        classes[1] = new ClassBean(1,"企业要闻");
        classes[2] = new ClassBean(2,"医疗新闻");
        classes[3] = new ClassBean(3,"生活贴士");
        classes[4] = new ClassBean(4,"药品新闻");
        classes[5] = new ClassBean(5,"疾病快讯");
        classes[6] = new ClassBean(6,"食品新闻");
        classes[7] = new ClassBean(7,"社会热点");
        return classes;
    }
}
