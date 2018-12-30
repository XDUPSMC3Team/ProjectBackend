package cn.xuyangl.onlineshopping.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;


/**
 * @Description 日期工具类
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/10/13 16:12
 */
public class DateUtil {


    /**
     * 获取本周开始时间
     * @return
     */
    public static LocalDateTime getBeginDayOfWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        //默认周日是 1
        if (dayOfWeek == 1)
        {
            dayOfWeek+=7;
        }
        calendar.add(Calendar.DATE,2-dayOfWeek);
        return getDateStartTime(calendar.getTime());
    }

    /**
     *  获取本周结束时间
     */
    public static LocalDateTime getEndDayOfWeek(Date date)
    {

        Calendar calendar = Calendar.getInstance();
        // 给本周开始时间加6
        LocalDateTime beginDayOfWeek = getBeginDayOfWeek(date);
        LocalDateTime localDateTime = beginDayOfWeek.plusDays(6);
        return LocalDateTime.of(localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth(),23,59,59,0);
    }

    /**
     *  获取本月开始时间
     */
    public static LocalDateTime getBeginDayOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),date.getMonth(),1);
        return getDateStartTime(calendar.getTime());
    }

    /**
     *  获取本月结束时间
     */
    public static LocalDateTime getEndDayOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),date.getMonth(),1);
        // 获得该月总共有多少天
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(calendar.get(Calendar.YEAR),date.getMonth(),actualMaximum);
        return getDateEndTime(calendar.getTime());
    }

    /**
     *  获取某个日期的开始时间
     */

    public static LocalDateTime getDateStartTime(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        if (date!=null)
        {
            calendar.setTime(date);
        }
        return LocalDateTime.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH),0,0,0,0);
    }

    /**
     *  获得某个日期的结束时间
     */
    public static LocalDateTime getDateEndTime(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        if (date!=null)
        {
            calendar.setTime(date);
        }
        return LocalDateTime.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH),23,59,59,999);
    }

    /**
     *  获取某年的开始时间
     */
    public static LocalDateTime getStartDayOfYear(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        if (date!=null)
        {
            calendar.setTime(date);
        }
//        calendar.set(calendar.get(Calendar.YEAR),0,1,0,0,0);
//        return new Timestamp(calendar.getTimeInMillis());
        return LocalDateTime.of(calendar.get(Calendar.YEAR),1,1,0,0,0,0);
    }

    /**
     *  获取某年的借书时间
     */
    public static LocalDateTime getEndDayOfYear(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        if (date!=null)
        {
            calendar.setTime(date);
        }
//        calendar.set(calendar.get(Calendar.YEAR),11,31,23,59,59);
//        calendar.set(Calendar.MILLISECOND,999);
//        return new Timestamp(calendar.getTimeInMillis());
        return LocalDateTime.of(calendar.get(Calendar.YEAR),12,31,23,59,59,999);
    }



}


