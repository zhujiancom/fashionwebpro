package com.zj.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

import com.zj.common.log.Log;


/**
 * 日期工具类
 * @author jk
 */
public class DateUtil extends DateUtils{
    /**
     * 默认日期格式, yyyy-MM-dd
     */
    private static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 默认时间格式, yyyy-MM-dd hh24:mm:ss
     */
    private static String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 不可被实例化
     */
    private DateUtil() {};

    /**
     * 获取日期字符串
     * @param date 日期
     * @return yyyy-MM-dd格式, 中国时间({@link Locale}.PRC)
     */
    public static String date2Str(Date date) {
    	// 使用默认日期格式
        return date2Str(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取日期字符串
     * @param date 日期
     * @param pattern 日期格式
     * @return 参数pattern指定日期格式, 中国时间({@link Locale}.PRC)
     */
    public static String date2Str(Date date, String pattern) {
    	// 使用上海时间
        return date2Str(date, pattern, TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 获取日期字符串
     * @param date 日期
     * @param locale 地区
     * @return yyyy-MM-dd格式, 参数locale指定地区的时间
     */
    public static String date2Str(Date date, TimeZone timeZone) {
        return date2Str(date, DEFAULT_DATE_PATTERN, timeZone);
    }

    /**
     * 获取日期字符串
     * @param date 日期
     * @param pattern 格式
     * @param locale 地区
     * @return pattern指定格式, locale指定区域的时间
     */
    public static String date2Str(Date date, String pattern, TimeZone timeZone) {

        String formatedDate = "";
        if (date != null) {
            DateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(timeZone);
            formatedDate = format.format(date);
        }

        return formatedDate;
    }

    /**
     * 获取时间字符串
     * @param date 时间
     * @retrn yyyy-MM-dd hh24:mm:ss 格式时间, 中国地区({@link Locale}.PRC)
     */
    public static String time2Str(Date date) {
        return date2Str(date, DEFAULT_TIME_PATTERN);
    }


    /**
     * 获取时间字符串
     * @param date 时间
     * @param pattern 时间格式
     * @retrn pattern指定格式时间, 中国地区({@link Locale}.PRC)
     */
    public static String time2Str(Date date, String pattern) {
        return date2Str(date, pattern, TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 获取时间字符串
     * @param date 时间
     * @param locale 地区
     * @return yyyy-MM-dd hh24:mm:ss 格式时间, locale指定地区
     */
    public static String time2Str(Date date, TimeZone timeZone) {
        return date2Str(date, DEFAULT_TIME_PATTERN, timeZone);
    }

    /**
     * 获取时间字符串
     * @param date 时间
     * @param pattern 时间格式
     * @param locale 地区
     * @return yyyy-MM-dd hh24:mm:ss 格式时间, locale指定地区
     */
    public static String time2Str(Date date, String pattern, TimeZone timeZone) {
        return date2Str(date, pattern, timeZone);
    }

    /**
     * add by dkw 20071101
     * get date from string
     * @param dateStr date string
     * @param pattern date format pattern
     * @param loacle locale
     * @return date object
     */
    public static Date getDate(String dateStr, String pattern, Locale locale) {
        Date result = null;

        if (dateStr == null || dateStr.trim().length() < 10) {
            return null;
        }
        else {
            if (dateStr.trim().length() >= pattern.length()) {
                dateStr = dateStr.substring(0, pattern.length());
            }
            try {
                result = new SimpleDateFormat(pattern, locale).parse(dateStr);
            }
            catch (Exception e) {
                Log.error(DateUtil.class, "parse date fail: " + e.getMessage(), e);
            }
        }

        return result;
    }

    /**
     * add by dkw 20071101
     * get date from string, parse date using default locale
     * @param dateStr date string
     * @param pattern date format pattern
     * @return date object
     */
    public static Date getDate(String dateStr, String pattern) {
        // Log.debug(DateUtil.class, "", "parse date using default locale: " + Locale.getDefault());
        return getDate(dateStr, pattern, Locale.getDefault());
    }

    /**
     * get date from string, parse date using default locale<br>
     * and default pattern 'yyyy-MM-dd'
     * @param dateStr date string
     * @return date object
     */
    public static Date getDate(String dateStr) {
        return getDate(dateStr, DEFAULT_DATE_PATTERN, Locale.getDefault());
    }
    
    
    /**
     * add by LH 2010-12-01
     * 
     * 一个月中的最后一天
     * @param year
     * @param month
     * @return
     */
    public static int getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * add by zhujian 
     * @param timeMills  毫秒数
     * @return
     */
    public static Date getDateFromMills(Long timeMills){
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(timeMills);
    	return cal.getTime();
    }
}

