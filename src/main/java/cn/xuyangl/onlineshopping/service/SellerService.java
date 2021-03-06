package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.entity.OrderDetail;
import cn.xuyangl.onlineshopping.entity.Seller;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.model.OrderData;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.util.List;

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
     Result register(Seller seller);

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
     ResultEnum registerShop(Shop shop);

    /**
     *  查询某个商家的所有的销售历史
     * @param shopId
     * @return
     */
     Result findSaleHistory(Integer shopId);

    /**
     *  查看所有已付款商品信息
     * @param shopId
     * @return
     */
     Result findAllPayedOrder(Integer shopId);


    /**
     *  查询每年每月每日每周的历史销售记录
     * @param shopId
     * @param date
     * @return
     */
     Result findSaleHistoryByDate(Integer shopId, Date date);

    /**
     *  查询每年每月每日每周的营业额
     */
    Result findIncomeHistoryByDate(Integer shopId,Date date);

    /**
     *  根据shopId 查询账户余额
     * @param shopId
     * @return
     */
    Result findAccountByShopId(Integer shopId);

}
