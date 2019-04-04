package com.example.zhongahiyi.healthy.view.bean.news;

public class Constant {
    public static final String ARGUMENT_CLASS_ID = "argument_class_id";
    public static final String ARGUMENT_NEWS_ID = "argument_news_id";
    public static final String NEWS_IMAGE_PREFIX = "http://www.1ccf.com/";
    public static final String URL_NEWS_LIST = "http://a.apix.cn/yi18/news/list";
    public static final String URL_NEWS_DETAIL = "http://a.apix.cn/yi18/news/show";
    public static final String NEWS_APIX_KEY = "5e6f5ba0034b4b89533f220db763b904";
    public static final int  NEWS_LIMIT = 20;
    public static final String  NEWS_TYPE_ID = "id";    //最新时间
    public static final String  NEWS_TYPE_COUNT = "count";  //访问数


    public static final ClassBean[] getNewsClass(){
        ClassBean[] classes = new ClassBean[7];
        classes[0] = new ClassBean(1,"企业要闻");
        classes[1] = new ClassBean(2,"医疗新闻");
        classes[2] = new ClassBean(3,"生活贴士");
        classes[3] = new ClassBean(4,"药品新闻");
        classes[4] = new ClassBean(5,"疾病快讯");
        classes[5] = new ClassBean(6,"食品新闻");
        classes[6] = new ClassBean(7,"社会热点");
        return classes;
    }
}
