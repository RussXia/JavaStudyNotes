package com.xzy.commons_lang3.time;

/**
 * Created by RuzzZZ on 11/09/2017.
 */
public class TimeDemo1 {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
//        String date = DateFormatUtils.format(new Date(),DATE_PATTERN);
//        System.out.println(date);
//        date = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(new Date());
//        System.out.println(date);
        Long s1 = 993213L;
        s1 = s1 / 100;
        s1 = s1 * 100;
        System.out.println(s1);
    }
}
