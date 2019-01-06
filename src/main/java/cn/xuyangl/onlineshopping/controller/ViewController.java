package cn.xuyangl.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2019/1/6 20:50
 */
@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping("/")
    public String customer()
    {
        return "customer";
    }

    @GetMapping("/customer")
    public String customer2()
    {
        return "customer";
    }
    @GetMapping("/seller")
    public String seller()
    {
        return "seller";
    }
    @GetMapping("/admin")
    public String admin()
    {
        return "admin";
    }
}
