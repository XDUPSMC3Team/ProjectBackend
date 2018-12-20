package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.model.SimpleOrderForm;

/**
 * @author xjin
 * created on 2018/12/20 9:37 AM
 */
public interface BuyerOrderService {
    // 直接下单
    void simpleOrder(SimpleOrderForm sof, Integer buyerId);

    // 购物车下单
    void cartOrder();
}
