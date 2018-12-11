package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.BuyerDao;
import cn.xuyangl.onlineshopping.dao.ProductCollectDao;
import cn.xuyangl.onlineshopping.dao.ShopCollectDao;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author xjin
 * created on 2018/11/30 4:28 PM
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private BuyerDao buyerDao;
    private ProductCollectDao productCollectDao;
    private ShopCollectDao shopCollectDao;

    @Autowired
    BuyerServiceImpl(BuyerDao buyerDao, ProductCollectDao productCollectDao, ShopCollectDao shopCollectDao) {
        this.buyerDao = buyerDao;
        this.productCollectDao = productCollectDao;
        this.shopCollectDao = shopCollectDao;
    }

    @Override
    public ResultEnum register(Buyer buyer) {
        // TODO email check

        // check duplicate username and email
        if (buyerDao.findByUsername(buyer.getUsername()) != null) return ResultEnum.RegisterUsernameAlreadyExist;
        if (buyerDao.findByEmail(buyer.getEmail()) != null) return ResultEnum.RegisterEmailAlreadyExist;
        buyerDao.saveAndFlush(buyer);
        return ResultEnum.Success;
    }

    @Override
    public Buyer login(String username, String password) {
        Buyer buyer = buyerDao.findByUsername(username);
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
        ProductCollect productCollect = new ProductCollect();
        productCollect.setBuyerId(buyerId);
        productCollect.setProductId(productId);
        productCollect.setCreateTime(LocalDateTime.now());
        productCollect.setUpdateTime(LocalDateTime.now());
        ProductCollect pc = productCollectDao.saveAndFlush(productCollect);
        return pc.getId();
    }

    @Override
    public Integer collectShop(Integer buyerId, Integer shopId) {
        ShopCollect shopCollect = new ShopCollect();
        shopCollect.setBuyerId(buyerId);
        shopCollect.setShopId(shopId);
        shopCollect.setCreateTime(LocalDateTime.now());
        shopCollect.setUpdateTime(LocalDateTime.now());
        ShopCollect sc = shopCollectDao.saveAndFlush(shopCollect);
        return sc.getId();
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
}
