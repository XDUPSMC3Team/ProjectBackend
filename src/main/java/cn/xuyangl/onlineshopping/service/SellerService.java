package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.entity.Seller;
import cn.xuyangl.onlineshopping.entity.Shop;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @Description seller 注册service
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 10:42
 */
public interface SellerService {

    /**
     *  注册一个seller
     * @param seller
     * @return
     */
     Boolean register(Seller seller);

    /**
     *  根据邮箱查询seller
     * @param email
     * @return
     */
     Seller findByEmail(String email);

    /**
     *  注册一个商家
     * @param shop
     * @return
     */
     boolean registerShop(Shop shop);
}
