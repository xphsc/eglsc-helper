package com.eglsc.util;

import org.joda.time.DateTime;

import java.util.Date;



public class DateUtil {

    private static class SingletonDateTime {
        static final  DateTime INSTANCE = new DateTime();;
    }

    public static DateTime getInstance() {
        return SingletonDateTime.INSTANCE;
    }


    public  static String lastYearStartDate(){
        String  date=  String.format(getInstance().now().plusYears(-1).dayOfYear().withMinimumValue()
                .toString("yyyy-MM-dd"));
        return date;
    }

    public  static String lastYearendDate(){
        String  date=  String.format(getInstance().now().plusYears(-1).dayOfYear().withMaximumValue()
                .toString("yyyy-MM-dd"));
        return date;
    }

    public static  String thisYearEndDate(){
        String  date=  String.format(getInstance().now().dayOfYear().withMaximumValue()
                .toString("yyyy-MM-dd"));
        return date;
    }

    public static String thisYearStartDate(){
        String  date=  String.format(getInstance().now().dayOfYear().withMinimumValue()
                .toString("yyyy-MM-dd"));
        return date;
    }

    public static Object thisYearStartDate(Object o){
        if(o instanceof String){
            String  date=  String.format(getInstance().now().dayOfYear().withMinimumValue()
                    .toString("yyyy-MM-dd"));
            return date;
        }
        if(o instanceof Long){
            long  date=  getInstance().now().dayOfYear().withMinimumValue().getMillis();
            return date;
        }
        return null;
    }

    public static String  nextYearStartDate(){
        String  date=  String.format(getInstance().now().plusYears(1).dayOfYear().withMinimumValue()
                .toString("yyyy-MM-dd"));
        return date;
    }

    public static String nextYearEndDate(){
        String  date=  String.format(getInstance().now().plusYears(1).dayOfYear().withMaximumValue()
                .toString("yyyy-MM-dd"));
        return date;
    }

    public static String DateTimeToString(DateTime d){
        String date = d.toString("yyyy-MM-dd");
        return date;
    }

    public static String DateTimeToTimeString(DateTime d){
        String date = d.toString("yyyy-MM-dd HH:mm:ss");
        return date;
    }

    public static Long DateTimeToLong(DateTime d){
        long date = d.getMillis();
        return date;
    }

    public static Long Date_hourMinSecondToZeroLong(DateTime d){
        long  date = d.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
        return date;
    }

    public static Long Date_hourMinSecondToZeroLong(){
        long  date =getInstance().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
        return date;
    }


    public static String longTimeToString(Long time){
        DateTime datetime= getInstance().withMillis(time);
        String date=datetime.toString("yyyy-MM-dd HH:mm:ss");
        return date;
    }

    public static DateTime StringTimeToDateTime(String time){
        DateTime date= getInstance().parse(time);
        return date;
    }

    public static Date DateTimeToDate(DateTime d){
        long time = d.getMillis();
        Date date=new Date();
        date.setTime(time);
        return date;
    }

    public static DateTime DateToDateTime(Date d){
        long time = d.getTime();
        DateTime date=getInstance().withMillis(time);
        return date;
    }


    public static void main(String[] args)  {
        long s=1;
        System.out.println( thisYearStartDate(new Long(1)));
        System.out.println(DateTimeToString(DateTime.now()));;
        System.out.println(nextYearStartDate());
        System.out.println(longTimeToString(new Long("1483239911061")));
        System.out.println(StringTimeToDateTime("2017-02-09"));
        System.out.println(DateTimeToDate(DateTime.now()));
        System.out.println("----------------");
        System.out.println(DateToDateTime(new Date()));
        System.out.println(Date_hourMinSecondToZeroLong(DateTime.now()));

    }
}


