package com.eglsc.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class TimeUtil {
    public static final long SEC_MILLIS = 1000L;
    public static final long MIN_MILLIS = 60000L;
    public static final long HOUR_MILLIS = 3600000L;
    public static final long DAY_MILLIS = 86400000L;
    public static final long WEEK_MILLIS = 604800000L;
    public static final long MONTH_MILLIS = 2592000000L;
    public static final long YEAR_MILLIS = 31536000000L;
    public static final int SEC = 1;
    public static final int MIN_SEC = 60;
    public static final int HOUR_SEC = 3600;
    public static final int DAY_SEC = 86400;
    public static final int WEEK_SE = 604800;
    public static final int MONTH_SEC = 2592000;
    public static final int YEAR_SEC = 31536000;

    public TimeUtil() {
    }

    public static int days(Date time) {
        long startTim = time.getTime();
        long endTim = System.currentTimeMillis();
        long diff = endTim - startTim;
        int days = (int)(diff / 1000L / 3600L / 24L);
        return days;
    }

    public static String time(Integer time) {
        return time(Long.valueOf((long)time.intValue()));
    }

    public static String time(Long time) {
        long hour = time.longValue() / 3600000L;
        time = Long.valueOf(time.longValue() % 3600000L);
        long min = time.longValue() / 60000L;
        time = Long.valueOf(time.longValue() % 60000L);
        long sec = time.longValue() / 1000L;
        time = Long.valueOf(time.longValue() % 1000L);
        return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(hour), Long.valueOf(min), Long.valueOf(sec)});
    }

    public static Date todayStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date todayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 24);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date yesterdayStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(5, -1);
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date yesterdayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date thisWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(7, 2);
        return cal.getTime();
    }

    public static Date thisWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(thisWeekStart());
        cal.add(7, 7);
        return cal.getTime();
    }

    public static Date thisMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(5, cal.getActualMinimum(5));
        return cal.getTime();
    }

    public static Date thisMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 24);
        return cal.getTime();
    }

    public static Date thisYearEnd() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format.parse(year + "-12-31 23:59:59");
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date thisYearStart() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format.parse(year + "-01-01 00:00:00");
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date lastYearEnd() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1) - 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format.parse(year + "-12-31 23:59:59");
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date lastYearStart() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1) - 1;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format.parse(year + "-01-01 00:00:00");
        } catch (ParseException var4) {
            return null;
        }
    }

    public static boolean setSystemTime(Date date) {
        String osName = System.getProperty("os.name");

        try {
            String cmd;
            SimpleDateFormat dateFormat;
            SimpleDateFormat timeFormat;
            if(osName.matches("^(?i)Windows.*$")) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                timeFormat = new SimpleDateFormat("HH:mm:ss");
                cmd = String.format("cmd /c time %s", new Object[]{timeFormat.format(date)});
                Runtime.getRuntime().exec(cmd);
                cmd = String.format("cmd /c date %s", new Object[]{dateFormat.format(date)});
                Runtime.getRuntime().exec(cmd);
            } else {
                dateFormat = new SimpleDateFormat("yyyyMMdd");
                timeFormat = new SimpleDateFormat("HH:mm:ss");
                cmd = String.format("date -s %s", new Object[]{dateFormat.format(date)});
                Runtime.getRuntime().exec(cmd);
                cmd = String.format("date -s %s", new Object[]{timeFormat.format(date)});
                Runtime.getRuntime().exec(cmd);
            }

            return true;
        } catch (IOException var6) {
            return false;
        }
    }

}
