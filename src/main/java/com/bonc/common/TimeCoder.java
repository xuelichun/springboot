package com.bonc.common;

/**
 * 
 * <p>Title: BONC - TimeCoder </p>
 * 
 * <p>Description: 时间加密、解密的辅助类 </p>
 * 
 * <p>Copyright: Copyright BONC(c) 2013 - 2025 </p>
 * 
 * <p>Company: 北京东方国信科技股份有限公司 </p>
 * 
 * @author yangdx
 * @version 1.0.0
 */
public class TimeCoder {

    /**
     * 加码、解码规则
     */
    static int dayN = 24 * 3600;
    static int yearN = 400 * dayN;
    static int monthN = 32 * dayN;
    static int hourN = 3600;
    static int mmN = 60;
    static int ssN = 1;

    /**
     * 编码时间
     *
     * @param date 时间字符串yyyyMMddHHmmss
     * @return 编码过后的时间
     */
    public static int encode(String date) {
        if (date == null) {
            return 0;
        }

        date = date.trim();

        int year = Integer.parseInt(date.substring(0, 4)) - 2000;
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        int hour = Integer.parseInt(date.substring(8, 10));
        int mm = Integer.parseInt(date.substring(10, 12));
        int ss = Integer.parseInt(date.substring(12, 14));

        return year * yearN + month * monthN + day * dayN + hour * hourN + mm * mmN + ss;
    }

    /**
     * 解码时间
     *
     * @param date 时间对象
     * @return 时间字符串 yyyyMMddHHmmss
     */
    public static String decode(int date) {
        int year = date / yearN + 2000;
        int month = date % yearN / monthN;
        int day;
        if (month > 0)
            day = (date - ((year - 2000) * yearN)) % monthN / dayN;
        else {
            day = (date - ((year - 2001) * yearN)) % monthN / dayN;
        }

        int hour = date % dayN / hourN;
        int mm = date % hourN / mmN;
        int ss = date % mmN;

        if (month > 0)
            return new StringBuilder("" + year)
                    .append(fillZero(month))
                    .append(fillZero(day))
                    .append(fillZero(hour))
                    .append(fillZero(mm))
                    .append(fillZero(ss)).toString();

        else {
            return new StringBuilder("" + (year - 1))
                    .append("" + 12)
                    .append(fillZero(day))
                    .append(fillZero(hour))
                    .append(fillZero(mm))
                    .append(fillZero(ss)).toString();
        }
    }

    /**
     * 小于10的数字前面加0
     *
     * @param num 数字
     * @return 小于10的数字变为0x
     */
    private static String fillZero(int num) {
        if (num < 10) return "0" + num;

        return "" + num;
    }
}
