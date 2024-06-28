package com.lf.util.date;


import com.lf.util.log.AppLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppDateFormatUtil {

    private static SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat hhmmssFormat = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat yyyyMMddHHmmssFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    private final static ThreadLocal<SimpleDateFormat> dateFormaterFull = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    /**
     * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd HH:mm:ss:SSS)
     * @return String 当前系统时间
     */
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
    }


    /**
     * 系统时间转换成指定格式(格式：yyyy年MM月dd日 HH时mm分ss秒)
     * @return String 当前系统时间
     */
    public static String getCurrentTime2() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
    }

    /**
     * 系统时间转换成指定格式(格式：yyyy年MM月dd日 HH时mm分ss秒SSS毫秒)
     * @return String 当前系统时间
     */
    public static String getCurrentTime3() {
        return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒") .format(new Date());
    }


    /**
     * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd HH:mm)
     * @return String 当前系统时间
     */
    public static String getCurrentTime4(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }


    /**
     * 系统时间转换成指定格式(格式：yyyy年MM月dd日 HH时mm分)
     * @return String 当前系统时间
     */
    public static String getCurrentTime5() {
        return new SimpleDateFormat("yyyy年MM月dd日  HH时mm分").format(new Date());
    }


    /**
     * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd)
     * @return String 当前系统时间
     */
    public static String getCurrentTimeYMD() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }


    /**
     * 系统时间转换成指定格式(格式：yyy年MM月dd日)
     * @return String  当前系统时间
     */
    public static String getCurrentTimeYMD2(){
        return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
    }


    /**
     * 通过时间字符串转换成指定格式
     * @param date 时间值
     * @param dataFormat 格式为(yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss)
     * @return String 返回格式化时间值
     */
    public static Date getFormatDateByString(String date, String dataFormat) {
        try {
            return new SimpleDateFormat(dataFormat).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            AppLog.e("AppSysDateMgr-->>getFormatDateByString", e.getMessage().toString());
            return null;
        }
    }


    /**
     * 通过时间字符串转换成指定格式
     * @param date 时间值
     * @param dataFormat 格式为(yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss)
     * @param chianDataFormat 格式可为(yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss) 或 (yyyy年MM月dd日 或 yyyy年MM月dd日 HH时mm分ss秒)
     * @return String 返回格式化时间值
     */
    public static String getFormatDateByString(String date, String dataFormat, String chianDataFormat) {
        try {
            return new SimpleDateFormat(chianDataFormat).format(new SimpleDateFormat(dataFormat).parse(date));
        } catch (ParseException e) {
            AppLog.e("AppSysDateMgr-->>getFormatDateByString", e.getMessage().toString());
            return null;
        }
    }




    /**
     * 通过时间字符串转换成指定格式(格式：yyyy年MM月dd日)
     * @param date 时间值
     * @return String 返回格式化时间值
     */
    public static String getFormatDateByString(String date){
        try {
            return new SimpleDateFormat("yyyy年MM月dd日").format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            AppLog.e("AppSysDateMgr-->>getFormatDateByString", e.getMessage().toString());
            return null;
        }
    }

    /**
     * 获取参数时间的年
     * @param date 时间值
     * @return String 返回年
     */
    public static String getYear(Date date){
        return new SimpleDateFormat("yyyy").format(date);
    }

    /**
     * 获取参数时间的月
     * @param date 时间值
     * @return String 返回月
     */
    public static String getMonth(Date date){
        return new SimpleDateFormat("MM").format(date);
    }


    /**
     * 获取参数时间的天
     * @param date  时间值
     * @return String 返回天
     */
    public static String getDay(Date date){
        return new SimpleDateFormat("dd").format(date);
    }



    /**
     * 获取当前时间的下个月份(格式：yyyy-MM-dd)
     * @return String 当前时间下个月份
     */
    public static String getNextMonth() {
        int year = 0;
        int moth = 0;
        int day = 0;
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        moth = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH) + 2;
        return year + "-" + moth + "-" + day;
    }

    /**
     * 判断日期是否属于今天日期(精确到天)
     * @param sDate 日期值
     * @return boolean 返回true表示是，false表示不是
     */
    public static boolean isToday(String sDate) {
        boolean falg = false;
        try {
            Date date = null;
            date = dateFormaterFull.get().parse(sDate);
            Date today = new Date();
            if (date != null) {
                String nowDate = dateFormater.get().format(today);
                String timeDate = dateFormater.get().format(date);
                if (nowDate.equals(timeDate)) {
                    falg = true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            AppLog.e("AppSysDateMgr-->>getSysIsToday", e.getMessage().toString());
        }
        return falg;
    }

    /**
     * 检查日期是否有效
     * @param year 年
     * @param month 月
     * @param day 日
     * @return boolean
     */
    public static boolean getDateIsTrue(String year, String month, String day){
        try {
            String data = year + month + day;
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
            simpledateformat.setLenient(false);
            simpledateformat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            AppLog.e("AppSysDateMgr-->>getDateIsTrue", e.getMessage().toString());
            return false;
        }
        return true;
    }

    /**
     * 判断两个字符串日期的前后
     * @param strdate1  字符串时间1
     * @param strdate2  字符串时间2
     * @return boolean
     * 日期与时间
     */
    public static boolean getDateIsBefore(String strdate1, String strdate2) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            AppLog.d("AppSysDateMgr-->>getDateIsBefore-->>strdate1: ", strdate1);
            AppLog.d("AppSysDateMgr-->>getDateIsBefore-->>strdate2: ", strdate2);
            return df.parse(strdate1).before(df.parse(strdate2));
        } catch (ParseException e) {
            e.printStackTrace();
            AppLog.e("AppSysDateMgr-->>getDateIsBefore", e.getMessage().toString());
            return false;
        }
    }
    /**
     * 判断两个字符串日期的前后
     * @param strdate1  字符串时间1
     * @param strdate2  字符串时间2
     * @return boolean
     */
    public static boolean getDateIsEqual(String strdate1, String strdate2){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.parse(strdate1).equals(df.parse(strdate2));
        } catch (ParseException e) {
            e.printStackTrace();
            AppLog.e("AppSysDateMgr-->>getDateIsBefore", e.getMessage().toString());
            return false;
        }
    }


    /**
     * 判断两个时间日期的前后
     * @param date1  日期1
     * @param date2  日期2
     * @return boolean
     */
    public static boolean getDateIsBefore(Date date1, Date date2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return getDateIsBefore(df.format(date1), df.format(date2));
    }

    /**
     * 判断两个字符串日期的前后
     * @param strdate1  字符串时间1
     * @param strdate2  字符串时间2
     * @return boolean
     * 日期比较
     *
     * create by huangcheng
     */
    public static boolean getDateIsBeforeYYMMDD(String strdate1, String strdate2){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            AppLog.i("AppSysDateMgr-->>getDateIsBefore-->>strdate1: ", strdate1);
            AppLog.i("AppSysDateMgr-->>getDateIsBefore-->>strdate2: ", strdate2);
            return df.parse(strdate1).before(df.parse(strdate2));
        } catch (ParseException e) {
            e.printStackTrace();
            AppLog.e("AppSysDateMgr-->>getDateIsBefore", e.getMessage().toString());
            return false;
        }
    }

    /**
     *  日期格式字符串转换成时间戳
     *
     *  create by fuxiaosong
     * @return
     */
    public static long date2TimeStamp(String date, SimpleDateFormat dateFormat){
        try {
            return dateFormat.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的分钟
     *
     * create by fuxiaosong
     */
    public static long getMinuteBetweenTwoDate(String dateBegin , String dateEnd){
        long millisBegin = date2TimeStamp(dateBegin , yyyyMMddHHmmssFormat);
        long millisEnd = date2TimeStamp(dateEnd , yyyyMMddHHmmssFormat);
        return (millisEnd - millisBegin) / 60;
    }

}
