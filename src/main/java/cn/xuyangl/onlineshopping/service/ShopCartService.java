package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.VO.ShopCartVO;
import cn.xuyangl.onlineshopping.model.ProductCartForm;

import java.util.List;

/**
 * @author xjin
 * created on 2018/12/19 2:45 PM
 */
public interface ShopCartService {
    void addCart(ProductCartForm productCartForm, Integer buyerId);

    void updateCart(Integer cartId, Integer amount);

    List<ShopCartVO> viewCart(Integer buyerId);

    void deleteCart(Integer cartId);

}
