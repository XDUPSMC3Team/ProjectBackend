package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.SellerDao;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.Seller;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.service.SellerService;
import cn.xuyangl.onlineshopping.utils.MailUtil;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 10:43
 */
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     *  seller 注册
     * @param seller
     * @return
     */
    @Override
    public Result register(Seller seller) {

        Seller sellerByUsername = sellerDao.findSellerByUsername(seller.getUsername());
        if (sellerByUsername!=null)
        {
            return ResultUtil.error(ResultEnum.RegisterUsernameAlreadyExist);
        }
        if (sellerDao.findSellerByEmail(seller.getEmail())!=null)
        {
            return ResultUtil.error(ResultEnum.RegisterEmailAlreadyExist);
        }
        // 补充默认属性的值
        seller.setStatus(0);    // 表示为正常用户
        seller.setCreateTime(LocalDateTime.now());
        // 查找用户是否存在
        sellerDao.save(seller);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage register_success = MailUtil.createMessage(seller.getEmail(), "【注册成功】", "Welcome to our website!");
                javaMailSender.send(register_success);
            }
        }).start();
        return ResultUtil.success();
    }

    @Override
    public Seller findByEmail(String username) {
        Seller sellerByEmail = sellerDao.findSellerByEmailOrUsername(username,username);
        return sellerByEmail;
    }

    @Override
    public boolean registerShop(Shop shop) {
        // 补全属性
        shop.setStatus(0);  // 设置为受理中
        shop.setCreateTime(LocalDateTime.now());
        shop.setUpdateTime(LocalDateTime.now());
        try{
            shopDao.save(shop);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
