package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.entity.Seller;
import cn.xuyangl.onlineshopping.service.SellerService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description seller 控制器
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 10:48
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     *  seller 注册
     * @param seller
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Seller seller) {
        Boolean register = sellerService.register(seller);
        if (register) {
            sellerService.register(seller);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.RegisterError);
        }
    }

    /**
     *  seller 登录
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(String email,String password)
    {
        Seller byEmail = sellerService.findByEmail(email);
        if (byEmail==null)
        {
            return ResultUtil.error(ResultEnum.AccountNotFound);
        }
        if (byEmail.getPassword()!=null&&byEmail.getPassword().equals(password))
        {
            // 判断密码是否相等
            return ResultUtil.success();
        }else{
            return ResultUtil.error(ResultEnum.PasswordIncorrect);
        }
    }
}
