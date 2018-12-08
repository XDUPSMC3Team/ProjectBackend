package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.Buyer;

/**
 * @author xjin
 * created on 2018/11/30 4:07 PM
 */
public interface BuyerService {

    Boolean register(Buyer buyer);

    Buyer login(String username, String password);

    Buyer findByUsername(String username);

    Buyer findByEmail(String email);
}
