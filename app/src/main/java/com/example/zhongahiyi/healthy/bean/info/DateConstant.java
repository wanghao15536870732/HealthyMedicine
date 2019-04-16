package com.example.zhongahiyi.healthy.bean.info;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConstant {

    public static final String ARGUMENT_Date_ID = "argument_date_id";

    /**
     * 计算当前日期前后几天的日期
     * @param distanceDay
     * @return
     */
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // LogUtil.d("前7天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    /**
     * 转换
     * @param time
     * @return
     */
    public static String transform(String time){
        String temp = "";
        temp = time.replace("-","年").replace("-","月") + "日";
        return temp;
    }
    public static final DateBean[] getInfoDate() {
        DateBean[] dateBeans = new DateBean[7];
        dateBeans[0] = new DateBean(0, transform(getOldDate(0)));
        dateBeans[1] = new DateBean(1, transform(getOldDate(1)));
        dateBeans[2] = new DateBean(2, transform(getOldDate(2)));
        dateBeans[3] = new DateBean(3, transform(getOldDate(3)));
        dateBeans[4] = new DateBean(4, transform(getOldDate(4)));
        dateBeans[5] = new DateBean(5, transform(getOldDate(5)));
        dateBeans[6] = new DateBean(6, transform(getOldDate(6)));
        return dateBeans;
    }
}
