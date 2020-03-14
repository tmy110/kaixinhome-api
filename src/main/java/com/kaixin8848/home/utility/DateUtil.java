package com.kaixin8848.home.utility;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 *
 * @author lisong
 */
public class DateUtil {

    /**
     * 日期格式
     **/
    public static final String DATE_PATTERN_HH_MM = "HH:mm";
    public static final String DATE_PATTERN_HH_MM_SS = "HH:mm:ss";
    public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS_1 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN_MM_DD_HH_MM_CN = "MM月dd日 HH:mm";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_CN = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_PATTERN_YYYY_MM_DD_1 = "yyyyMMdd";
    public static final String DATE_PATTERN_YYYY_MM_DD_HH_MM_1 = "yyyyMMddHHmm";
    public static final String DATE_PATTERN_YYYY_MM_DD_2 = "yyyy/MM/dd";

    public static String date2String(Date date) {
        return date2String(date, DATE_PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    public static String date3String(Date date) {
        return date2String(date, DATE_PATTERN_YYYY_MM_DD_HH_MM_1);
    }

    public static String date4String(Date date) {
        return date2String(date, DATE_PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    public static String date2String1900(Date date) {
        if (("1900-01-01").equals(date2String(date, DATE_PATTERN_YYYY_MM_DD))) {
            return "";
        }
        return date2String(date);
    }

    public static String date2String1900(Date date, String pattern) {
        if (("1900-01-01").equals(date2String(date, DATE_PATTERN_YYYY_MM_DD))) {
            return "";
        }
        return date2String(date, pattern);
    }


    public static String date2String(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date string2Date(String dateString) {
        return string2Date(dateString, DATE_PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    public static Date string2Date(String dateString, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        return format.parse(dateString, pos);
    }

    public static Date getMonthStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getMonthEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    public static Date getDayStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDayEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }

    public static Date addYear(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, amount);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        return cal.getTime();
    }

    public static Date addDay(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    public static Date addHour(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, amount);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }

    /**
     * 日期比较
     *
     * @param startDate
     * @param compDate
     * @param endDate
     * @return
     */
    public static boolean compareDate(Date startDate, Date compDate, Date endDate) {
        boolean flag = false;
        if (compare_date(compDate, startDate) >= 0 && compare_date(compDate, endDate) <= 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 日期比较
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare_date(Date date1, Date date2) {
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String getExactDateStr(String date) {
        if (StringUtils.isNotBlank(date)) {
            if (date.length() == 8) {
                return date;
            } else {
                return date.replaceAll("-", "");
            }
        }
        return "";
    }

    /**
     * 获取日期是周几
     *
     * @param date 0：周日，1-6：周一至周六
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 转换成时间，字符串格式为 yyyy-MM-dd,yyyyMMdd
     *
     * @param date
     * @return
     */
    public static Date convert2Date(String date, String pattern) {
        String dateString = StringUtils.EMPTY;
        if (date.length() == 8) {
            dateString = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        } else {
            dateString = date;
        }

        return string2Date(dateString, pattern);
    }

    /**
     * 获取时间的年部分
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取时间的月部分
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获取时间的日部分
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取时间的小时部分
     *
     * @param date
     * @return
     */
    public static int getHours(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取时间的小时部分
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    /**
     * 获取时间的小时部分
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    /**
     * utc时间转换（形如Date(456123456142+0800)）
     *
     * @param utcDate
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date convertUtcDate(String utcDate) {
        try {
            if (utcDate.toLowerCase().contains("date")) {
                utcDate = utcDate.substring(utcDate.indexOf("(") + 1, utcDate.lastIndexOf("+"));
            }
            return new Date(Long.valueOf(utcDate));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将时间戳转换为时间
     *
     * @param timespan
     * @return
     */
    public static String stampToDate(String timespan){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(timespan);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取系统时间
     *
     * @param pattern
     * @return
     */
    public static String DateNow(String pattern){
        SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
        return df.format(new Date());
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String DateNow(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }
    /**
     * 如20180901
     *
     * @return
     */
    public static String getYYYYMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

}
