package com.tingtingfm.lapp.a.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 *
 * @author lqsir
 */
public class TimeUtils {
    /**
     * 将给定的时间值转换成H:M:S，当小时为0时，只显示M:S
     *
     * @param time 要转换的时间值
     * @return H:M:S/M:S
     */
    public static String converToHms(int time) {
        int h = 0, m = 0, s = 0;
        s = time % 60;
        time = time / 60;
        m = time % 60;
        h = time / 60;
        return h == 0 ? getDoubleNum(m) + ":" + getDoubleNum(s)
                : getDoubleNum(h) + ":" + getDoubleNum(m) + ":" + getDoubleNum(s);
    }

    private static String getDoubleNum(int value) {
        return value > 9 ? value + "" : (value == 0 ? "00" : "0" + value);
    }

    public static int getTotalTime(String st) {
        int totalTime = 0;
        String[] times = st.split(":");
        for (int i = times.length; i > 0; i--) {
            totalTime += Integer.valueOf(times[i - 1]) * Math.pow(60, times.length - i);
        }

        return totalTime;
    }

    /**
     * 根据提供的格式返回对应的时间字符串
     *
     * @param format {@link TimeFormat}
     * @return
     */
    public static String getTimeForSpecialFormat(TimeFormat format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getValue(), Locale.CHINA);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getTimeForSpecialFormat(TimeFormat format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getValue(), Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 给定的日期是否是今天
     *
     * @param date 给定的日期
     * @return true 表示今天 flase 表示非今天
     * @author lqsir
     */
    public static boolean isTodayForDate(String date) {
        return getTimeForSpecialFormat(TimeFormat.TimeFormat2).equals(date);
    }

    /**
     * 比较给定的2个时间值是否相等
     *
     * @param value
     * @param date
     * @return
     */
    public static boolean isEqualsForDates(long value, String date) {
        return getTimeForFormatAndDate(TimeFormat.TimeFormat2, value).equals(date);
    }

    /**
     * 以给定的格式、给定的时间值来返回相应结果
     *
     * @param value 当前时间秒值
     * @return 返回当前秒值的年月日
     * @author lqsir
     */
    public static String getTimeForFormatAndDate(TimeFormat format, long value) {
        if (value == 0l)
            return "";

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(value * 1000);
        return getTimeForSpecialFormat(format, c.getTime());
    }

    public static final String getTimeByCalendar() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时
        int minute = cal.get(Calendar.MINUTE);// 分

        return hour + ":" + getDoubleNum(minute);
    }

    public static String millisToString(long millis) {
        String value = "00:00:00";

        if (millis <= 0)
            return value;

        millis = Math.abs(millis);

        millis /= 1000;
        int sec = (int) (millis % 60);
        millis /= 60;
        int min = (int) (millis % 60);
        millis /= 60;
        int hours = (int) millis;

        String time;
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        format.applyPattern("00");

        time = format.format(hours) + ":" + format.format(min) + ":" + format.format(sec);

        return time;
    }

    /**
     * 显示倒计时，只有分+秒
     *
     * @param millis
     * @return
     */
    public static String countdownString(long millis) {
        String value = "00:00";
        boolean negative = millis <= 0;

        if (negative)
            return value;

        millis = Math.abs(millis);

        millis /= 1000;
        int sec = (int) (millis % 60);
        millis /= 60;
        int min = (int) (millis % 60);
        millis /= 60;
        int hours = (int) millis;
        min = hours * 60 + min;

        String time;
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        format.applyPattern("00");

        time = format.format(min) + ":" + format.format(sec);

        return time;
    }


    private static String timeToStr(String st) {
        return getTimeForSpecialFormat(TimeFormat.TimeFormat6) + ":" + st + ":00";
    }

    public static long getMillis(String str) {
        String[] tt = str.split(":");
        int h = Integer.valueOf(tt[0]);
        int s = Integer.valueOf(tt[1]);

        return (h * 3600 + s * 60) * 1000;
    }

    /**
     * 得到当天到当前时刻的秒数
     *
     * @return
     */
    public static int getCurrentSecondForDay() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.println(hour + ":" + minute + ":" + second);
        return hour * 3600 + minute * 60 + second;
    }

    public enum TimeFormat {
        TimeFormat1("yyyy.MM.dd"),
        TimeFormat2("yyyy-MM-dd"),
        TimeFormat3("yyyyMMddHHmmss"),
        TimeFormat4("yyyy-MM-dd HH:mm:ss"),
        TimeFormat5("yy:MM:dd:HH:mm:ss"),
        TimeFormat6("yyyy:MM:dd");

        String format;

        TimeFormat(String value) {
            format = value;
        }

        public String getValue() {
            return format;
        }
    }

}
