package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.BuyerDao;
import cn.xuyangl.onlineshopping.entity.Buyer;
import cn.xuyangl.onlineshopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xjin
 * created on 2018/11/30 4:28 PM
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private BuyerDao buyerDao;

    @Autowired
    BuyerServiceImpl(BuyerDao buyerDao) {
        this.buyerDao = buyerDao;
    }

    @Override
    public Boolean register(Buyer buyer) {
        // TODO email check

        // check duplicate username
        if (buyerDao.findByUsername(buyer.getUsername()) != null)
            return false;
        buyerDao.saveAndFlush(buyer);
        return true;
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
}
