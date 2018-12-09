package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/8 22:53
 */
@RestController
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/mail",method = RequestMethod.POST)
    public String sendMail()
    {
        SimpleMailMessage message = MailUtil.createMessage("1187697635@qq.com", "密码找回", "12314");
        javaMailSender.send(message);
        return "success";
    }
}
