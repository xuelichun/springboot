package com.bonc.utils;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Title: BONC - DateHelper </p>
 * 
 * <p>Description: 处理时间的辅助类，需要joda-time开源库辅助 </p>
 * 
 * <p>Copyright: Copyright BONC(c) 2013 - 2025 </p>
 * 
 * <p>Company: 北京东方国信科技股份有限公司 </p>
 * 
 * @author yangdx
 * @version 1.0.0
 */
public class DateHelper {

    /**
     * 获取某个时间点以前，当天已经发生过的小时
     *
     * @param dateTime 某天结束时间
     * @return 某天所有小时字符串,格式HH
     */
    public static List<String> getHoursOfDate(DateTime dateTime) {
        List<String> retList = new ArrayList<String>();

        // 获取一天的开始时间
        DateTime beginTime = dateTime.withTimeAtStartOfDay();
        while (beginTime.isBefore(dateTime)) {
            // 加入返回集合
            retList.add(beginTime.toString("HH"));

            // 小时+1
            beginTime = beginTime.plusHours(1);
        }

        return retList;
    }

    /**
     * 获取某两个时间点的所有天数
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param pattern   返回格式
     * @return 某两个时间点之间的所有天数
     */
    public static List<String> getBetweenDays(DateTime beginTime, DateTime endTime, String pattern) {
        List<String> retList = new ArrayList<String>();

        // 处理时间
        while (beginTime.isBefore(endTime)) {
            System.out.println("61@@@"+beginTime.toString(pattern)+"@@@"+beginTime+"@@@"+endTime);
            retList.add(beginTime.toString(pattern));
            // 天数+1
            beginTime = beginTime.plusDays(1);
        }

        return retList;
    }

    /**
     * 获取某两个时间点的所有天数
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 某两个时间点之间的所有天数 格式：yyyy-MM-dd HH:mm:ss
     */
    public static List<String> getBetweenDays(DateTime beginTime, DateTime endTime) {

        return getBetweenDays(beginTime, endTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将一个时间字符串成joda-time时间
     *
     * @param date    源字符串
     * @param pattern 格式
     * @return
     */
    public static DateTime parseDate(String date, String pattern) {
        // 格式化对象
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            // 返回时间
            return new DateTime(dateFormat.parse(date).getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取两个时间区间对象
     *
     * @param begin 开始时间
     * @param end 结束时间
     * @return 两个时间区间
     */
    public static Duration getDuration(DateTime begin, DateTime end) {

        return new Duration(begin, end);
    }

    /**
     * 获取两个时间区间对象
     *
     * @param begin 开始时间 yyyyMMddHHmmss
     * @param end 结束时间 yyyyMMddHHmmss
     * @return 两个时间区间
     */
    public static Duration getDuration(String begin, String end) {

        return new Duration(parseDate(begin), parseDate(end));
    }

    /**
     * 获取两个时间区间对象
     *
     * @param begin   开始时间
     * @param end     结束时间
     * @param pattern 时间格式
     * @return 两个时间区间
     */
    public static Duration getDuration(String begin, String end, String pattern) {

        return new Duration(parseDate(begin, pattern), parseDate(end, pattern));
    }

    /**
     * 字符串转成joda-time对象
     *
     * @param date 时间字符串 yyyyMMddHHmmss
     * @return joda-time对象
     */
    public static DateTime parseDate(String date) {

        return parseDate(date, "yyyyMMddHHmmss");
    }

    /**
     * 字符串转成joda-time对象
     *
     * @param millis 毫秒时间
     * @return joda-time对象
     */
    public static DateTime parseDate(long millis) {

        return new DateTime(millis);
    }

    /**
     * 获取某月最后一天,最后一小，最后一分钟，最后一秒
     *
     * @param date 传入时间对象
     * @param pattern 日期格式
     * @param tarPattern 返回时间格式
     * @return 某月最后一天
     */
    public static String getLastDayOfMonth(String date, String pattern, String tarPattern) {

        return parseDate(date, pattern).dayOfMonth().withMaximumValue().secondOfDay().withMaximumValue().toString(tarPattern);
    }

    /**
     * 获取某月第一天
     *
     * @param date 传入时间对象
     * @param pattern 日期格式
     * @param tarPattern 返回时间格式
     * @return 某月第一天
     */
    public static String getFirstDayOfMonth(String date, String pattern, String tarPattern) {

        return parseDate(date, pattern).dayOfMonth().withMinimumValue().toString(tarPattern);
    }

}
