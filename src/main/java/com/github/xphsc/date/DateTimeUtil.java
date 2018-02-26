package com.github.xphsc.date;


import com.github.xphsc.util.RegexUtil;
import org.joda.time.DateTime;
import java.util.Date;


public class DateTimeUtil {

    private static class SingletonDateTime {
        static final  DateTime INSTANCE = new DateTime();;
    }

    public static DateTime dateTime() {
        return SingletonDateTime.INSTANCE;
    }


    public  static String lastYearStartOfDate(){
        String  date=  String.format(DateTime.now().plusYears(-1).dayOfYear().withMinimumValue()
                .toString(DateFormat.DATE_FORMAT_DAY));
        return date;
    }

    public  static String lastYearStartOfDate(String dateFormat){
        String  date=  String.format(DateTime.now().plusYears(-1).dayOfYear().withMinimumValue()
                .toString(dateFormat));
        return date;
    }

    public  static String lastYearEndDate(){
        String  date=  String.format(DateTime.now().plusYears(-1).dayOfYear().withMaximumValue()
                .toString(DateFormat.DATE_FORMAT_DAY));
        return date;
    }

    public  static String lastYearEndOfDate(String dateFormat){
        String  date=  String.format(DateTime.now().plusYears(-1).dayOfYear().withMaximumValue()
                .toString(dateFormat));
        return date;
    }

    public static  String thisYearEndOfDate(){
        String  date=  String.format(DateTime.now().dayOfYear().withMaximumValue()
                .toString(DateFormat.DATE_FORMAT_DAY));
        return date;
    }

    public static  String thisYearEndOfDate(String dateFormat){
        String  date=  String.format(DateTime.now().dayOfYear().withMaximumValue()
                .toString(dateFormat));
        return date;
    }
    public static String thisYearStartOfDate(){
        String  date=  String.format(DateTime.now().dayOfYear().withMinimumValue()
                .toString(DateFormat.DATE_FORMAT_DAY));
        return date;
    }

    public static String thisYearStartOfDate(String dateFormat){
        String  date=  String.format(DateTime.now().dayOfYear().withMinimumValue()
                .toString(dateFormat));
        return date;
    }



    public static String  nextYearStartOfDate(){
        String  date=  String.format(DateTime.now().plusYears(1).dayOfYear().withMinimumValue()
                .toString(DateFormat.DATE_FORMAT_DAY));
        return date;
    }

    public static String  nextYearStartOfDate(String dateFormat){
        String  date=  String.format(DateTime.now().plusYears(1).dayOfYear().withMinimumValue()
                .toString(dateFormat));
        return date;
    }

    public static String nextYearEndOfDate(){
        String  date=  String.format(DateTime.now().plusYears(1).dayOfYear().withMaximumValue()
                .toString(DateFormat.DATE_FORMAT_DAY));
        return date;
    }

    public static String nextYearEndOfDate(String dateFormat){
        String  date=  String.format(DateTime.now().plusYears(1).dayOfYear().withMaximumValue()
                .toString(dateFormat));
        return date;
    }

    public static String dateTimeToString(DateTime d){
        String date = d.toString(DateFormat.DATE_FORMAT_DAY);
        return date;
    }

    public static String dateTimeToString(DateTime d, String dateFormat){
        String date="";
        if (RegexUtil.isMatched(dateFormat, DateFormat.DATE_FORMAT_DAY)) {
            date =  d.toString(DateFormat.DATE_FORMAT_DAY);
        }
        if (RegexUtil.isMatched(dateFormat, DateFormat.DATE_FORMAT_DAY_SLASH)) {
            date = d.toString(DateFormat.DATE_FORMAT_DAY_SLASH);
        }
        if (RegexUtil.isMatched(dateFormat, DateFormat.TIME_FORMAT_SEC)) {
            date = d.toString(DateFormat.TIME_FORMAT_SEC);
        }
        if (RegexUtil.isMatched(dateFormat,DateFormat.DATE_FORMAT_SEC)) {
            date = d.toString(DateFormat.DATE_FORMAT_SEC);
        }
        if (RegexUtil.isMatched(dateFormat, DateFormat.DATE_FORMAT_MSEC)) {
            date = d.toString(DateFormat.DATE_FORMAT_MSEC);
        }
        if (RegexUtil.isMatched(dateFormat,DateFormat.DATE_FORMAT_MSEC_T)) {
            date = d.toString(DateFormat.DATE_FORMAT_MSEC_T);
        }
        if (RegexUtil.isMatched(dateFormat,DateFormat.DATE_FORMAT_MSEC_T_Z)) {
            date = d.toString(DateFormat.DATE_FORMAT_MSEC_T_Z);
        }
        return date;
    }

    public static String dateTimeToTimeString(DateTime d){
        String date = d.toString(DateFormat.DATE_FORMAT_MSEC);
        return date;
    }

    public static Long dateTimeToLong(DateTime d){
        long date = d.getMillis();
        return date;
    }

    public static Long dateTimeHMSToZero(DateTime d){
        long  date = d.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
        return date;
    }

    public static Long dateTimeHMSToZero(){
        long  date =dateTime().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
        return date;
    }

    public static DateTime dateTimePlusDays(DateTime d,Integer plusDays){
        DateTime  dateTime =d.plusDays(plusDays);
        return dateTime;
    }


    public static Long dateTimePlusDaysHMSToZero(DateTime d,Integer plusDays){
        long  date =d.plusDays(plusDays).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
        return date;
    }

    public static String longTimeToString(Long time){
        DateTime datetime= dateTime().withMillis(time);
        String date=datetime.toString(DateFormat.DATE_FORMAT_MSEC);
        return date;
    }

    public static String longTimeToString(Long time,String dateFormat){
        DateTime datetime= dateTime().withMillis(time);
        String date=datetime.toString(dateFormat);
        return date;
    }

    public static DateTime StringTimeToDateTime(String time){
        DateTime date= DateTime.parse(time);
        return date;
    }

    public static String StringTimeToDateFormat(String time,String dateFormat){
        DateTime dateTime= DateTime.parse(time);
        String date=dateTime.toString(dateFormat);
        return date;
    }

    public static Date dateTimeToDate(DateTime d){
        long time = d.getMillis();
        Date date=new Date();
        date.setTime(time);
        return date;
    }

    public static DateTime dateToDateTime(Date d){
        long time = d.getTime();
        DateTime date=dateTime().withMillis(time);
        return date;
    }



}


