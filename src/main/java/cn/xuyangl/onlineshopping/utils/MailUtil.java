package cn.xuyangl.onlineshopping.utils;

import org.springframework.mail.SimpleMailMessage;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/8 22:49
 */
public class MailUtil {

    public  static  final  String From = "ericfbt@163.com";

    /**
     *
     * @param to
     * @param subject
     * @param content
     * @return
     */
    public static SimpleMailMessage createMessage(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(From);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        return simpleMailMessage;
    }
}
