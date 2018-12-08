package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.entity.Buyer;
import cn.xuyangl.onlineshopping.service.BuyerService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author xjin
 * created on 2018/11/30 3:36 PM
 */
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private BuyerService buyerService;

    @Autowired
    BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/register")
    public Result register(String email, String username, String password) {
        Buyer buyer = new Buyer();
        buyer.setEmail(email);
        buyer.setUsername(username);
        buyer.setPassword(password);
        if (buyerService.register(buyer)) {
            return ResultUtil.success("Welcome, " + username);
        } else {
            return ResultUtil.error(1, "the username you detected has been register.");
        }
    }

    @PostMapping("/login")
    public Result login(String username, String password, HttpSession session) {
        Buyer buyerInDB = buyerService.findByUsername(username);
        if (buyerInDB == null) return ResultUtil.error(1, "buyer account isn't exist.");
        Buyer buyer = buyerService.login(username, password);
        if (buyer == null) return ResultUtil.error(2, "password uncorrect.");
        session.setAttribute(Common.USER_TYPE, "buyer");
        session.setAttribute(Common.BUYER_ID, buyer.getId());
        session.setAttribute(Common.USERNAME, buyer.getUsername());
        session.setMaxInactiveInterval(3600);
        return ResultUtil.success();
    }

    @GetMapping("/personal/test")
    public Result test() {
        return ResultUtil.success("enter");
    }
}
