package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.*;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.model.ProductCartForm;
import cn.xuyangl.onlineshopping.model.SimpleOrderForm;
import cn.xuyangl.onlineshopping.service.BuyerService;
import cn.xuyangl.onlineshopping.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author xjin
 * created on 2018/11/30 4:28 PM
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private BuyerDao buyerDao;
    private ProductDao productDao;
    private ProductCollectDao productCollectDao;
    private ShopCollectDao shopCollectDao;
    private ShopCartDao shopCartDao;
    private JavaMailSender javaMailSender;

    @Autowired
    BuyerServiceImpl(BuyerDao buyerDao,
                     ProductCollectDao productCollectDao,
                     ShopCollectDao shopCollectDao,
                     ProductDao productDao,
                     ShopCartDao shopCartDao,
                     JavaMailSender javaMailSender) {
        this.buyerDao = buyerDao;
        this.productCollectDao = productCollectDao;
        this.shopCollectDao = shopCollectDao;
        this.productDao = productDao;
        this.shopCartDao = shopCartDao;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public ResultEnum register(Buyer buyer) {
        // TODO email check
        // check duplicate username and email
        if (buyerDao.findByUsername(buyer.getUsername()) != null) return ResultEnum.RegisterUsernameAlreadyExist;
        if (buyerDao.findByEmail(buyer.getEmail()) != null) return ResultEnum.RegisterEmailAlreadyExist;
        buyerDao.saveAndFlush(buyer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage register_success = MailUtil.createMessage(buyer.getEmail(), "【注册成功】", "Congratulations\n. You have successfully registered us. Have a shopping experience.");
                javaMailSender.send(register_success);
            }
        }).start();

        return ResultEnum.Success;
    }

    @Override
    public Buyer login(String username, String password) {
        Buyer buyer = buyerDao.findByUsernameOrEmail(username, username);
        if (password.equals(buyer.getPassword())) {
            return buyer;
        } else {
            return null;
        }
    }

    @Override
    public Buyer findByUsername(String username) {
        return buyerDao.findByUsername(username);
    }

    @Override
    public Buyer findByEmail(String email) {
        return buyerDao.findByEmail(email);
    }

    @Override
    public Buyer save(Buyer buyer) {
        return buyerDao.saveAndFlush(buyer);
    }

    @Override
    public Buyer findById(Integer id) {
        return buyerDao.findById(id);
    }

    @Override
    public Integer collectProduct(Integer buyerId, Integer productId) {
        ProductCollect pcInDb = productCollectDao.findByBuyerIdAndProductId(buyerId, productId);
        if (pcInDb == null) {
            ProductCollect productCollect = new ProductCollect();
            productCollect.setBuyerId(buyerId);
            productCollect.setProductId(productId);
            productCollect.setCreateTime(LocalDateTime.now());
            productCollect.setUpdateTime(LocalDateTime.now());
            ProductCollect pc = productCollectDao.saveAndFlush(productCollect);
            return pc.getId();
        }
        return pcInDb.getId();
    }

    @Override
    public Integer collectShop(Integer buyerId, Integer shopId) {
        ShopCollect scInDb = shopCollectDao.findByBuyerIdAndShopId(buyerId, shopId);
        if (scInDb == null) {
            ShopCollect shopCollect = new ShopCollect();
            shopCollect.setBuyerId(buyerId);
            shopCollect.setShopId(shopId);
            shopCollect.setCreateTime(LocalDateTime.now());
            shopCollect.setUpdateTime(LocalDateTime.now());
            ShopCollect sc = shopCollectDao.saveAndFlush(shopCollect);
            return sc.getId();
        }
        return scInDb.getId();
    }

    @Override
    public Boolean cancelCollect(Integer collectId, Integer type) {
        if (type == 1) {
            productCollectDao.delete(collectId);
        } else {
            shopCollectDao.delete(collectId);
        }
        return true;
    }

    @Override
    public ResultEnum findPassword(String username) {
        Buyer buyer = buyerDao.findByUsernameOrEmail(username, username);
        if (buyer == null) return ResultEnum.AccountNotFound;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage simpleMailMessage = MailUtil.createMessage(buyer.getEmail(),"【找回密码】Find Password", "Hello, You are finding your password in ParkNShop. Your password is: " + buyer.getPassword());
                javaMailSender.send(simpleMailMessage);
            }
        }).start();
        return ResultEnum.Success;
    }
}
