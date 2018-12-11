package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.entity.Buyer;

/**
 * @author xjin
 * created on 2018/11/30 4:07 PM
 */
public interface BuyerService {

    ResultEnum register(Buyer buyer);

    Buyer login(String username, String password);

    Buyer findByUsername(String username);

    Buyer findByEmail(String email);

    Buyer findById(Integer id);

    Buyer save(Buyer buyer);

    Integer collectProduct(Integer buyerId, Integer productId);

    Integer collectShop(Integer buyerId, Integer shopId);

    Boolean cancelCollect(Integer collectId, Integer type);


}
