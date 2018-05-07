package com.goyo.towermodule.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luteng
 * on 2018/1/31.
 */

public class DateUtils {
    private static SimpleDateFormat sf;
    private static SimpleDateFormat sdf;
    /**
     * 获取系统时间 格式为："yyyy/MM/dd "
     **/
    public static String getCurrentDate(String format) {
        if(TextUtils.isEmpty(format)){

            return null;
        }
        Date d = new Date();
        sf = new SimpleDateFormat(format);
        return sf.format(d);
    }

    /**
     * Date转时间
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date getDate(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取系统时间 格式为："yyyy "
     **/
    public static String getCurrentYear() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："MM"
     **/
    public static String getCurrentMonth() {
        Date d = new Date();
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："dd"
     **/
    public static String getCurrentDay() {
        Date d = new Date();
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTime() {
        long d = new Date().getTime() / 1000;
        return d;
    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToString(long time,String format) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat(format);
        return sf.format(d);
    }

    /**
     * 时间戳中获取年
     */
    public static String getYearFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 时间戳中获取月
     */
    public static String getMonthFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 时间戳中获取日
     */
    public static String getDayFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time,String format) {
        sdf = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
}
