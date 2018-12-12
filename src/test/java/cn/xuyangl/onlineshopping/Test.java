package cn.xuyangl.onlineshopping;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

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
        System.out.println(jsonObject.keys().next());
    }
}
