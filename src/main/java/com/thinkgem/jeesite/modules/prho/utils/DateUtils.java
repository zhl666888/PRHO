package com.thinkgem.jeesite.modules.prho.utils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;


public class DateUtils extends DateFormatUtils{
	private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yy-MM-dd-HH-mm-ss"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }


    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }


    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     *
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parseStrDate(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 判断字符串是否是日期
     *
     * @param timeString
     * @return
     */
    public static boolean isDate(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(timeString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否是时刻（"HH:mm" ） eg: 25:61 false 12:02 true
     *
     * @param timeString （"HH:mm"）
     * @return
     */
    public static boolean isDateTime(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.setLenient(false);
        try {
            timeString = getDate() + " " + timeString;
            Date date = format.parse(timeString);
            String strDate = format.format(date);
            return timeString.equals(strDate);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @return
     */
    public static String dateFormat(Date timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    public static String dateFormatYYYYMMDD(Date timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(timestamp);
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @return
     */
    public static String dateFormatMinute(Date timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(timestamp);
    }

    /**
     * 通过秒数转换成HH:mm:ss格式的str
     *
     * @param seconds
     * @return
     */
    public static String dateFormatFromSeconds(Integer seconds) {
        /*SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		return  format.format(seconds * 1000);*/
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (seconds <= 0)
            return "00:00";
        else {
            minute = seconds / 60;
            if (minute < 60) {
                second = seconds % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = seconds - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    /**
     * @param i
     * @return
     * @description 格式化时分秒
     */
    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 字符串转时间类型
     *
     * @param timestamp
     * @return
     */
    public static Date dateFormat(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取系统时间Timestamp
     *
     * @return
     */
    public static Timestamp getSysTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取给定日期的毫秒时间
     *
     * @return
     */
    public static Long getTime(Date date) {
        return date.getTime();
    }

    /**
     * 获取系统时间Date
     *
     * @return
     */
    public static Date getSysDate() {
        return new Date();
    }

    /**
     * 生成时间随机数
     *
     * @return
     */
    public static String getDateRandom() {
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return s;
    }

    /**
     * 比较两个时间的大小(若date1>=date2则返回true其它false)
     *
     * @return
     */
    public static Boolean comparTime(Date date1, Date date2) {
        long times1 = date1.getTime();
        long times2 = date2.getTime();
        if (times1 >= times2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个时间的大小以及最大允许时间(若date1>=date2则返回true其它false)
     *
     * @return
     */
    public static Boolean comparTime(Date date1, Date date2, int days) {
        long times1 = date1.getTime();
        long times2 = date2.getTime();
        long timesMax = getSumDay(date2, days).getTime();
        if (times1 >= times2 && times1 <= timesMax) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 两个时间的相隔的时间(小时)
     *
     * @return
     */
    public static double getIntervalHours(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000)) - (day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        Double m = (double) min / 60;
        double hours = day * 24 + hour + m;
        return hours;
    }

    /**
     * 获取天数
     * @param date1
     * @param date2
     * @return
     */
    public static int getIntervalDays(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long day = diff / (24 * 60 * 60 * 1000);
//        long hour = (diff / (60 * 60 * 1000)) - (day * 24);
//        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
//        Double m = (double) min / 60;
//        double hours = day * 24 + hour + m;
        return (int)day;
    }

//    public static void main(String[] arg1){
//        Date dateNow = new Date();
//        Date dateAfter = new Date();
//        Calendar calendar = Calendar.getInstance();//得到日历
////                        calendar.add(calendar.MONTH, + Integer.parseInt(drcList.get(i).getWarningTimeCostomer()));//设置为3个月后
//        calendar.add(calendar.MONTH, + 1);//设置为3个月后
//        dateAfter = calendar.getTime();
//        getIntervalDays(dateAfter,dateNow);
//    }

    /**
     * 两个时间的相隔的秒数(秒)
     *
     * @return
     */
    public static long getIntervalSeconds(Date date1, Date date2) {
        long diff = (date1.getTime() - date2.getTime()) / 1000;
        return diff;
    }

    /**
     * 获得两个日期差多长时间格式是（x时x分x秒）
     *
     * @return
     */
    public static String getShiFenMiao(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long hour = (diff / (60 * 60 * 1000));
        long min = ((diff / (60 * 1000)) - hour * 60);
        long ss = (diff / 1000) - hour * 60 * 60 - min * 60;
        String time = hour + "时" + min + "分" + ss + "秒";
        return time;
    }

    /**
     * 获得秒的时间格式（x时x分x秒）
     *
     * @return
     */
    public static String getShiFenMiaoFromSeconds(Integer seconds) {
        long hour = (seconds / (60 * 60));
        long min = ((seconds / (60)) - hour * 60);
        long ss = seconds - hour * 60 * 60 - min * 60;
        String time = hour + "时" + min + "分" + ss + "秒";
        return time;
    }

    /**
     * 两个时间的相隔的时间(分钟)
     *
     * @return
     */
    public static Long getIntervalMinutes(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (60 * 1000);
    }
    /**
     * 两个时间的相隔多少个月
     *
     * @return
     */
    public static Integer getIntervalMonth(Date date1, Date date2) throws Exception {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String d1=sdf.format(date1);
        String d2=sdf.format(date2);
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(sdf.parse(d1));
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(sdf.parse(d2));
        int c =(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
        return Math.abs(c);
    }
  /**
     * 获得两个日期之间的所有月份
     *
     * @return
     */
    public static List<String> getMonthBetween(Date minDate, Date maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * @param date time
     * @return Timestamp
     * @author Created by chenzheng
     * @description 在日期上加秒后计算出结果
     */
    public static Timestamp getSumSeconds(Date date, int seconds) {
        long times1 = date.getTime();
        long times2 = seconds * 1000;
        long sumTimes = times1 + times2;
        return new Timestamp(sumTimes);
    }



    /**
     * @param date
     * @param day
     * @return Timestamp
     * @author Created by chenzheng
     * @description 在日期上加减天数
     */
    public static Timestamp getSumDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);//把日期往后增加day天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return new Timestamp(date.getTime());
    }


    /**
     * 将timestamp转换成date Str
     *
     * @param tt
     * @return
     * @author gk
     */
    public static String timestampToDate(Timestamp tt) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(tt.getTime()));
    }

    /**
     * @param date
     * @param month
     * @return Timestamp
     * @author Created by chenzheng
     * @description 在日期上加减月数
     */
    public static Timestamp getSumMonth(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, month);//增加或者减少月数
        date = calendar.getTime();   //增加或者减少后的结果
        return new Timestamp(date.getTime());
    }

    /**
     * @param date
     * @param year
     * @return Timestamp
     * @author Created by chenzheng
     * @description 在日期上加减年
     */
    public static Timestamp getSumYear(Date date, int year) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.YEAR, year);//增加或者减少年
        date = calendar.getTime();   //增加或者减少后的结果
        return new Timestamp(date.getTime());
    }
    /**
     * @param date
     * @param day
     * @return Timestamp
     * @author Created by chenzheng
     * @description 在日期上加减日
     */
    public static Timestamp getSumDate(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);//增加或者减少日
        date = calendar.getTime();   //增加或者减少后的结果
        return new Timestamp(date.getTime());
    }


    public static Integer getWeekCounter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static Integer getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int mouth = calendar.get(Calendar.MONTH);
        // JDK think 2015-12-31 as 2016 1th week
        //如果月份是12月，且求出来的周数是第一周，说明该日期实质上是这一年的第53周，也是下一年的第一周
        if (mouth >= 11 && week <= 1) {
            week = 1;
        }
        return week;
    }

    public static String getWeekOfYearStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int mouth = calendar.get(Calendar.MONTH);
        int yearInt = calendar.get(Calendar.YEAR);
        // JDK think 2015-12-31 as 2016 1th week
        //如果月份是12月，且求出来的周数是第一周，说明该日期实质上是这一年的第53周，也是下一年的第一周
        if (mouth >= 11 && week <= 1) {
            yearInt += 1;
            week = 1;
        }
        String str = yearInt+"."+week;
        return str;
    }

    /**
     * @param dt
     * @param week
     * @return Timestamp
     * @author Created by chenzheng
     * @description 根据当前给出的日期，和周几，计算出到这个周几的具体日期
     */
    public static Timestamp getWeekOfDate(Date dt, String week) {
        String[] weekDays = {"0", "1", "2", "3", "4", "5", "6"};//周日 周一 周二 周三 周四 周五 周六
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < weekDays.length; i++) {
            cal.setTime(dt);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
            String weekDay = weekDays[w];
            if (!week.equals(weekDay)) {
                dt = DateUtils.getSumDay(dt, 1);//在原先给定的日期上加1
            } else {
                break;
            }
        }
        return new Timestamp(dt.getTime());
    }

    /**
     * @param planArrivalTime
     * @return String
     * @author Created by chenzheng
     * @description 格式化成每天几点
     */
    public static String formateForDay(Date planArrivalTime) {
        String str;
        Calendar cal = Calendar.getInstance();
        cal.setTime(planArrivalTime);
        //获得时
        String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
        //获得分
        String minute = cal.get(Calendar.MINUTE) + "";
        //获得秒
        String second = cal.get(Calendar.SECOND) + "";
        str = second + " " + minute + " " + hour +" " + "*"+" " +"*" + " " + "?";
        return str.trim();
    }
    /**
     * 获取指定日期月份
     */
    public static String getMonthsCounter(Date date) {
        return formatDate(date, "MM");
    }


    /**
     * 取得指定月份的最后一天
     *
     *
     * @return String
     */
    public static String getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取两个时间段的月份例如：
     * begin = "2016-6-28 10:21:34"
     * end = "2016-9-28 10:21:37"
     * 2016-06-28,2016-06-30
     * 2016-07-01,2016-07-31
     * 2016-08-01,2016-08-31
     * 2016-09-01,2016-09-28
     * @param begin
     * @param end
     * @return
     */
    public static String getDateInterval(Date begin, Date end) {
        // 开始日期不能大于结束日期
        if (!begin.before(end)) {
            return null;
        }

        Calendar cal_begin = Calendar.getInstance();
        cal_begin.setTime(begin);

        Calendar cal_end = Calendar.getInstance();
        cal_end.setTime(end);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuffer strbuf = new StringBuffer();

        while (true) {
            if (cal_begin.get(Calendar.YEAR) == cal_end.get(Calendar.YEAR)
                    && cal_begin.get(Calendar.MONTH) == cal_end
                    .get(Calendar.MONTH)) {
                strbuf.append(sdf.format(cal_begin.getTime())).append(" 00:00:00,")
                        .append(sdf.format(cal_end.getTime())).append(" 23:59:59;");
                break;
            }
            String str_begin = sdf.format(cal_begin.getTime());
            String str_end = getMonthEnd(cal_begin.getTime());
            strbuf.append(str_begin).append(" 00:00:00,").append(str_end).append(" 23:59:59;");
            cal_begin.add(Calendar.MONTH, 1);
            cal_begin.set(Calendar.DAY_OF_MONTH, 1);
            // String str_end =;
        }

        return strbuf.toString();

    }



//    public static void main(String [] args){
//        String d1 = "2016-12-18 00:00:00";
//        String d2 = "2016-12-18 00:00:00";
//        //String d2 = "2016-12-24 21:32:59";
//        //String d1 = "2017-01-01 00:00:00";
//        //String d2 = "2017-01-04 12:12:59";
//        Date d3= parseDate(d1);
//        Date d4= parseDate(d2);
//        String[] start = getWeekDate1(d3, d4).split(";");
//        for(int i=0;i<start.length;i++){
//            System.out.println(start[i]);
//        }
//    }




    /**
     * 根据日期获取日期所在周的第一天或第7天的日期
     * @return
     */
    public static String getWeekDate(Date date,String type){
        String dateString="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        if("start".equals(type)){
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            dateString=sdf.format(calendar.getTime())+" 00:00:00";
        }
        if("end".equals(type)){
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            calendar.add(Calendar.DAY_OF_MONTH,6);
            dateString=sdf.format(calendar.getTime())+" 23:59:59";
        }
        return dateString;
    }

    public static Date stampToDate(String str){
        long lt = new Long(str);
        Date date = new Date(lt);
        return date;
    }

    public static String getDate(String pattern, int addSeconds) {
        return DateFormatUtils.format(new Date(System.currentTimeMillis() + 1000 * addSeconds), pattern);
    }

    //计算两个日期相差年数
    /**
     * 两个时间的相隔多少个月
     *
     * @return
     */
    public static Integer getIntervalYear(Date date1, Date date2) throws Exception {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String d1=sdf.format(date1);
        String d2=sdf.format(date2);
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(sdf.parse(d1));
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(sdf.parse(d2));
        int c =(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR));
        return Math.abs(c);
    }
}
