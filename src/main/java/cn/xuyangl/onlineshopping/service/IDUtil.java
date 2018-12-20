package cn.xuyangl.onlineshopping.service;

import java.util.UUID;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/20 21:25
 */
public class IDUtil {

    public static String generateID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
