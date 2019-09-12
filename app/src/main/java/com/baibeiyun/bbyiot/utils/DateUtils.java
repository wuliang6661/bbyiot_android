package com.baibeiyun.bbyiot.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 *  时间的工具类
 */
public class DateUtils {


    /**
     * 获取当前时间戳
     */
    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }


    /**
     * 获取当前时间，指定格式
     * @param strFormat
     * @return
     */
    public static String getCurrentDate(String strFormat){
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        Date date = new Date();
        return format.format(date);
    }

    /**
     * 将字符串转为时间戳
     * time : 时间的字符串
     * format：格式
     */
    public static long getTimeStamp(String time, String format) {
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date;

            date = sdf.parse(time);
            long timeStamp = date.getTime();
            return timeStamp;
        } catch (Exception e) {
            LogUtils.w(e.getMessage());
            return 0;
        }
    }

    /**
     * 将时间戳转换为指定格式的字符串
     */
    public static String stamp2String(long time,String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String re_StrTime = sdf.format(new Date(time));
        return re_StrTime;
    }

    /**
     * 指定格式返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }


    public static String getRevisitDate(String preDate, int num, int unit){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        try {
            Date date  = format.parse(preDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            switch (unit){
                case 1:
                    calendar.add(Calendar.DAY_OF_MONTH, num);
                    break;
                case 2:
                    calendar.add(Calendar.WEEK_OF_YEAR, num);
                    break;
                case 3:
                    calendar.add(Calendar.MONTH, num);
                    break;
                case 4:
                    calendar.add(Calendar.YEAR, num);
                    break;
                default:
                    break;
            }
            return format.format(calendar.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            return preDate;
        }
    }

    public static String getPreDateHintText(String preDate, String curDate , int num, int unit){
        if (preDate == null){
            switch (unit){
                case 1:
                    return "距离首次"+num+"天";
                case 2:
                    return "距离首次"+num+"周";
                case 3:
                    return "距离首次"+num+"月";
                case 4:
                    return "距离首次"+num+"年";
                default:
                    return "";
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        try {
            Date datePre = format.parse(preDate);
            Date dateCur = format.parse(curDate);
            Calendar calendarPre = Calendar.getInstance();
            Calendar calendarCur = Calendar.getInstance();
            calendarPre.setTime(datePre);
            calendarCur.setTime(dateCur);

            long gap = (calendarCur.getTimeInMillis()-calendarPre.getTimeInMillis())/(1000*3600*24);

            return "距离上次" +gap +"天";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }




    public static int[] currentAppendDays(int days){
        int tmp[] = new int[3];
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_WEEK, days);
        tmp[0] = cal.get(Calendar.YEAR);
        tmp[1] = cal.get(Calendar.MONTH);
        tmp[2] = cal.get(Calendar.DAY_OF_MONTH);
        return tmp;
    }

    public static int[] currentAppendMonths(int month){
        int tmp[] = new int[3];
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        tmp[0] = cal.get(Calendar.YEAR);
        tmp[1] = cal.get(Calendar.MONTH);
        tmp[2] = cal.get(Calendar.DAY_OF_MONTH);
        return tmp;
    }

    public static String convertMonthDay(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd", Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return format1.format(cdate);
    }

    public static long getLongBetween(String date1, String date2){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        Date cdate1 = new Date();
        Date cdate2 = new Date();
        try {
            cdate1 = format.parse(date1);
            cdate2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cdate1.getTime()-cdate2.getTime();
    }

    public static boolean isBeforeCurrentDate(String year, String month, String day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            Date date = format.parse(year+"-"+(month+1)+"-"+day+" "+"23:59:59");
            if(date.getTime()> System.currentTimeMillis()){
                return false;
            }else{
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static long parseDate(String date, String withFormat){
        SimpleDateFormat wformat = new SimpleDateFormat(withFormat, Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = wformat.parse(date);
        } catch (ParseException e) {
            cdate = new Date();
            e.printStackTrace();
        }
        return cdate.getTime();
    }

    public static String parseDate(String date, String withFormat, String toFormat){
        SimpleDateFormat wformat = new SimpleDateFormat(withFormat, Locale.CHINA);
        SimpleDateFormat tformat = new SimpleDateFormat(toFormat, Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = wformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tformat.format(cdate);
    }


    public static String getSimpleTime(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm", Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(cdate);
    }

    public static String getAmPm(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cdate.getTime());
        return cal.get(Calendar.AM_PM)==0? "am":"pm";
    }


    public static String getSimpleDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(cdate);
    }

    public static String getSimpleDateComcat(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(cdate);
    }

    //  yyyy/MM/dd/HHmmssfff + userID
    public static String getOSSKeyPre(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/HHmmssfff");
        Date date = new Date();
        return format.format(date);
    }

    public static int getAge(String birthday){
        if (StringUtils.isEmpty(birthday)){
            return 0;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date current = new Date();
        String currentString = format.format(current);
        String birthdayString = birthday.replace("-","").trim();

        int c1 = StringUtils.toInt(currentString);
        int c2 = StringUtils.toInt(birthdayString);

        return (c1-c2)/10000;
    }


    public static String spanLengthOfTotal(String total, float length){
        long totalTime = parseTotalTime(total);
        if (totalTime==0){
            return "0%";
        }else{
            double d = length*100/totalTime;
            return formatFloat(d)+"%";
        }
    }

    public static String getDateJustDate(){
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String dateString = format.format(currentDate);
        return dateString;
    }

    public static String getDateYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String dateString = format.format(cal.getTimeInMillis());
        return dateString;
    }

    public static String getDateTommorrow(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String dateString = format.format(cal.getTimeInMillis());
        return dateString;
    }


    public static String getWeekFirstDay(){
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY){
//			cal.add(Calendar.WEEK_OF_YEAR, -1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String dateString = format.format(cal.getTime());
        return dateString;
    }

    public static String getWeekLastDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY){
//			cal.add(Calendar.WEEK_OF_YEAR, -1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        String dateString = format.format(cal.getTime());
        return dateString;
    }

    public static float formatFloat(double value) {
        DecimalFormat format = new DecimalFormat("#.00");
        return StringUtils.toFloat(format.format(value));
    }

    public static String convertDateWithFormat(Date date, String formatStr){
        if (date==null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }


    public static Date getSortEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND,59);
        return new Date(calendar.getTimeInMillis());
    }


    public static int[] getDate(){
        Calendar calendar = Calendar.getInstance();
        return new int[]{calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)};
    }

    public static String convertDatePickDateString(String year, String month, String day){
        Date date = convertDatePickDate(year,month,day);
        return convertDateWithFormat(date,"yyyy-MM-dd");
    }


    public static Date convertDatePickDate(String year, String month, String day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, StringUtils.toInt(year));
        calendar.set(Calendar.MONTH, StringUtils.toInt(month)-1);
        calendar.set(Calendar.DAY_OF_MONTH, StringUtils.toInt(day));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date convertFileDate(int year, int month, int day, int hour, int minute, int second){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        Date date = new Date();
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertSpan(float span){
        int tspan = (int)span;
        int hour =   tspan/3600;
        int minute = tspan%3600/60;
        int second = tspan%3600%60;
        StringBuilder builder = new StringBuilder();
        if (hour!=0){
            builder.append(hour+"时");
        }
        if (minute!=0){
            builder.append(minute+"分");
        }
        if (second!=0) {
            builder.append(second + "秒");
        }
        return builder.toString();
    }

    public static Date convertServerDate(String serverDate){
        SimpleDateFormat format;
        if (serverDate.contains("T")){
            format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        }else{
            format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        Date date = new Date();
        try {
            date = format.parse(serverDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertServerDate(String dateStr, String format){
        if (StringUtils.isEmpty(dateStr)||StringUtils.isEmpty(format)){
            return null;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        SimpleDateFormat format2 = new SimpleDateFormat(format, Locale.CHINA);
        Date cdate = new Date();
        try {
            cdate = format1.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(cdate);
    }

    public static String convert2DateString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date==null){
            return format.format(new Date());
        }
        return format.format(date);
    }

    public static String convertTotalTime(long totalDuration){
        int hour;int minute;int second;
        hour = (int)(totalDuration/(3600));
        minute = (int)(totalDuration%(3600)/60);
        second = (int)(totalDuration%3600%60);
        DecimalFormat format = new DecimalFormat("00");
        return format.format(hour)+":"+format.format(minute)+":"+format.format(second);
    }


    public static Long parseTotalTime(String totalTime){
        if (totalTime==null||totalTime.length()==0||!totalTime.contains(":")){
            return 0l;
        }
        String[] tems = totalTime.split(":");
        int hour = StringUtils.toInt(tems[0]);
        int minute = StringUtils.toInt(tems[1]);
        int second = StringUtils.toInt(tems[2]);

        return (long)(hour*3600+minute*60+second);
    }




    public static String formatTimeLength(String timeLength){
        if (StringUtils.isEmpty(timeLength)){
            return null;
        }
        String time[] = timeLength.split(":");
        if (time==null||time.length<3){
            return null;
        }
        return time[0]+"小时"+time[1]+"分"+time[2]+"秒";
    }

    public static Date formatEndDate(Date collectTime, long length){
        return new Date(collectTime.getTime()+length*1000);
    }


    public static Date formatEndDate(String collectTimeStr, long length){
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        Date collectTime = null;
        try {
            collectTime = format.parse(collectTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            collectTime = new Date();
        }
        return new Date(collectTime.getTime()+length*1000);
    }

    public static String formatEndTime(Date collectTime, long length){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (collectTime==null){
            return null;
        }
        Date date = new Date(collectTime.getTime()+length*1000);
        return format.format(date);
    }

    public static String getNoYearTime(String time){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = format1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("MM-dd HH:mm:ss");
        return format2.format(date);
    }


    public static String getFriendlyTime(String time){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = format1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String format = null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        format = "MM-dd HH:mm";

        if (hour > 17) {
            format = "MM-dd 晚上 hh:mm";

        }else if(hour >= 0 && hour <= 6){
            format = "MM-dd 凌晨 hh:mm";
        } else if (hour > 11 && hour <= 17) {
            format = "MM-dd 下午 hh:mm";
        } else {
            format = "MM-dd 上午 hh:mm";
        }
        SimpleDateFormat format2  = new SimpleDateFormat(format);
        return format2.format(date);
    }



    public static boolean isSameDay(String strDate, Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date pDate;
        try {
            pDate = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            pDate = new Date();
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(pDate);
        calendar2.setTime(date);
        if (calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR)
                &&calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH)
                &&calendar1.get(Calendar.DAY_OF_MONTH)==calendar2.get(Calendar.DAY_OF_MONTH)){
            return true;
        }
        return false;
    }


}
