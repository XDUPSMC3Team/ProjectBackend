package cn.xuyangl.onlineshopping;

import cn.xuyangl.onlineshopping.VO.StatusEnum;
import cn.xuyangl.onlineshopping.utils.DateUtil;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/12 10:20
 */

public class Test {


    @org.junit.Test
    public void test01() throws JSONException {
        String s = "{\"memory\":[\"4G\", \"8G\"], \"color\":[\"red\",\"black\", \"white\"]}";
        JSONObject jsonObject = new JSONObject(s);
        System.out.println(jsonObject.get(jsonObject.keys().next()+""));
    }

    @org.junit.Test
    public void test02(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(simpleDateFormat.format(new Date()));
//        System.out.println(StatusEnum.Received.code);

        Date date = new Date();
        System.out.println(DateUtil.getDateStartTime(date));
        System.out.println(DateUtil.getDateEndTime(date));
        System.out.println(DateUtil.getBeginDayOfWeek(date));
        System.out.println(DateUtil.getEndDayOfWeek(date));
        System.out.println(DateUtil.getBeginDayOfMonth(date));
        System.out.println(DateUtil.getEndDayOfMonth(date));
        System.out.println(DateUtil.getStartDayOfYear(date));
        System.out.println(DateUtil.getEndDayOfYear(date));
    }

}
