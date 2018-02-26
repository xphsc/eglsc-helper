package com.github.xphsc.date;


import com.github.xphsc.exception.UtilException;
import com.github.xphsc.util.RegexUtil;
import com.github.xphsc.util.StringUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class DateUtil {

    public DateUtil() {
    }
    public static String getYear() {
        return formatDate(new Date(),"yyyy");
    }
    public static String getMonth() {
        return formatDate(new Date(),"MM");
    }

    public static String getDay() {
        return formatDate(new Date(),"dd");
    }

    public static String formatDate(final Date date) {
        return formatDate(date, DateFormat.DATE_FORMAT_DAY);
    }

    public static String formatDate(final Date date, String format) {
        if (null == date || StringUtil.isBlank(format)) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDate(String format) {
        return formatDate(new Date(), format);
    }

    public static Date setYears(Date date, int amount) {
        return set(date, 1, amount);
    }

    public static Date setMonths(Date date, int amount) {
        return set(date, 2, amount);
    }

    public static Date setDays(Date date, int amount) {
        return set(date, 5, amount);
    }

    public static Date setHours(Date date, int amount) {
        return set(date, 11, amount);
    }

    public static Date setMinutes(Date date, int amount) {
        return set(date, 12, amount);
    }

    public static Date setSeconds(Date date, int amount) {
        return set(date, 13, amount);
    }

    public static Date setMilliseconds(Date date, int amount) {
        return set(date, 14, amount);
    }

    public static Date plusMonths(Date date, int months) {
        if (months == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static Date plusDays(final Date date, int days) {
        if (days == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);

        return cal.getTime();
    }


    public static Date plusMins(final Date date, int mins) {
        if (mins == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);

        return cal.getTime();
    }
    public static int days(Date time) {
        long startTim = time.getTime();
        long endTim = System.currentTimeMillis();
        long diff = endTim - startTim;
        int days = (int)(diff / 1000L / 3600L / 24L);
        return days;
    }

    public static String time(Integer time) {
        return time(Long.valueOf((long) time.intValue()));
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

    public static Date todayStartOfDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date todayEndOfDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 24);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date yesterdayStartOfDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(5, -1);
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date yesterdayEndOfDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date thisWeekStartOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(7, 2);
        return cal.getTime();
    }

    public static Date thisWeekEndOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(thisWeekStartOfWeek());
        cal.add(7, 7);
        return cal.getTime();
    }

    public static Date thisMonthStartOfDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(5, cal.getActualMinimum(5));
        return cal.getTime();
    }

    public static Date thisMonthEndOfDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 24);
        return cal.getTime();
    }

    public static Date thisYearEndOfDate() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1);
        SimpleDateFormat format = new SimpleDateFormat(DateFormat.DATE_FORMAT_SEC );

        try {
            return format.parse(year + "-12-31 23:59:59");
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date thisYearStartOfDate() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1);
        SimpleDateFormat format = new SimpleDateFormat(DateFormat.DATE_FORMAT_SEC);

        try {
            return format.parse(year + "-01-01 00:00:00");
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date lastYearEndOfDate() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1) - 1;
        SimpleDateFormat format = new SimpleDateFormat(DateFormat.DATE_FORMAT_SEC);

        try {
            return format.parse(year + "-12-31 23:59:59");
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date lastYearStartOfDate() {
        Calendar lastDate = Calendar.getInstance();
        int year = lastDate.get(1) - 1;
        SimpleDateFormat format = new SimpleDateFormat(DateFormat.DATE_FORMAT_SEC);

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
                dateFormat = new SimpleDateFormat(DateFormat.DATE_FORMAT_DAY);
                timeFormat = new SimpleDateFormat(DateFormat.TIME_FORMAT_SEC);
                cmd = String.format("cmd /c time %s", new Object[]{timeFormat.format(date)});
                Runtime.getRuntime().exec(cmd);
                cmd = String.format("cmd /c date %s", new Object[]{dateFormat.format(date)});
                Runtime.getRuntime().exec(cmd);
            } else {
                dateFormat = new SimpleDateFormat(DateFormat.DATE_FORMAT_DAY_PURE);
                timeFormat = new SimpleDateFormat(DateFormat.TIME_FORMAT_SEC);
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


    public static Date converToDateTime(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT_SEC);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
        }
        return null;
    }

    public static int getSecondsBetween(Date d1, Date d2) {
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Calendar[] cal = new Calendar[2];
        for (int i = 0; i < cal.length; i++) {
            cal[i] = Calendar.getInstance();
            cal[i].setTime(d[i]);
        }
        long m = cal[0].getTime().getTime();
        long n = cal[1].getTime().getTime();
        return (int) ((m - n) / 1000L);
    }
    //通过生日计算年龄
    public static int calCulateForDate(Date dataOfBirth){

        Calendar dob = Calendar.getInstance();
        dob.setTime(dataOfBirth);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    public static Date changeMongodbDate(){

        Date date = null;
        try {
            SimpleDateFormat format =  new SimpleDateFormat(DateFormat.DATE_FORMAT_SEC);
            String da = format.format(new Date());
            format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
            date = format.parse(da);
        } catch (Exception e) {

        }
        return date;

    }

    public static Date modifyDate(int unit,int num){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        Date date = AddAndSubTime(unit,num);
        return date;
    }
    public static Date AddAndSubTime(int currenTime,int num) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(currenTime, num);
        Date startDate = calendar.getTime();
        return startDate;
    }


    public static boolean inFormat(String sourceDate, String format) {
        if (sourceDate == null || StringUtil.isBlank(format)) {
            return false;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            dateFormat.parse(sourceDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameInstant(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            return date1.getTime() == date2.getTime();
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.getTime().getTime() == cal2.getTime().getTime();
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static long getFragmentInMilliseconds(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInSeconds(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.SECONDS);
    }

    public static long getFragmentInMinutes(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.MINUTES);
    }

    public static long getFragmentInHours(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.HOURS);
    }

    public static long getFragmentInDays(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.DAYS);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInSeconds(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.SECONDS);
    }

    public static long getFragmentInMinutes(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.MINUTES);
    }

    public static long getFragmentInHours(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.HOURS);
    }

    public static long getFragmentInDays(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.DAYS);
    }
    static
    public Date strToDate(String strDate, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Deprecated
    static
    public Date strToDate(String strDate) throws UtilException,
            ParseException {

        strDate = strDate.trim();
        SimpleDateFormat sdf = null;
        if (RegexUtil.isMatched(strDate, DateRegexPattern.DATE_REG)) {
            sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT_DAY);
        }
        if (RegexUtil.isMatched(strDate, DateRegexPattern.DATE_REG_SLASH)) {
            sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT_DAY_SLASH);
        }
        if (RegexUtil.isMatched(strDate, DateRegexPattern.TIME_SEC_REG)) {
            sdf = new SimpleDateFormat(DateFormat.TIME_FORMAT_SEC);
        }
        if (RegexUtil.isMatched(strDate, DateRegexPattern.DATE_TIME_REG)) {
            sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT_SEC);
        }
        if (RegexUtil.isMatched(strDate, DateRegexPattern.DATE_TIME_MSEC_REG)) {
            sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT_MSEC);
        }
        if (RegexUtil.isMatched(strDate, DateRegexPattern.DATE_TIME_MSEC_T_REG)) {
            sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT_MSEC_T);
        }
        if (RegexUtil.isMatched(strDate, DateRegexPattern.DATE_TIME_MSEC_T_Z_REG)) {
            sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT_MSEC_T_Z);
        }
        if (null != sdf) {
            return sdf.parse(strDate);
        }
        throw new UtilException(
                String.format("[%s] can not matching right time format", strDate));
    }

    static
    public Date stringToDateUnmatchToNull(String strDate) {
        Date date = null;
        try {
            date = strToDate(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    private static Date set(Date date, int calendarField, int amount) {
        if(date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setLenient(false);
            c.setTime(date);
            c.set(calendarField, amount);
            return c.getTime();
        }
    }

    private static long getFragment(Date date, int fragment, TimeUnit unit) {
        if(date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return getFragment(calendar, fragment, unit);
        }
    }

    private static long getFragment(Calendar calendar, int fragment, TimeUnit unit) {
        if(calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            long result = 0L;
            int offset = unit == TimeUnit.DAYS?0:1;
            switch(fragment) {
                case 1:
                    result += unit.convert((long)(calendar.get(6) - offset), TimeUnit.DAYS);
                    break;
                case 2:
                    result += unit.convert((long)(calendar.get(5) - offset), TimeUnit.DAYS);
            }

            switch(fragment) {
                case 1:
                case 2:
                case 5:
                case 6:
                    result += unit.convert((long)calendar.get(11), TimeUnit.HOURS) ;
                case 11:
                    result += unit.convert((long)calendar.get(12), TimeUnit.MINUTES);
                case 12:
                    result += unit.convert((long)calendar.get(13), TimeUnit.SECONDS);
                case 13:
                    result += unit.convert((long)calendar.get(14), TimeUnit.MILLISECONDS);
                case 14:
                    return result;
                case 3:
                case 4:
                case 7:
                case 8:
                case 9:
                case 10:
                default:
                    throw new IllegalArgumentException("The fragment " + fragment + " is not supported") ;
            }
        }
    }

}
