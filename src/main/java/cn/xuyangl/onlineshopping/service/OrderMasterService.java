package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.entity.OrderMaster;
import cn.xuyangl.onlineshopping.model.OrderData;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/19 14:27
 */
public interface OrderMasterService {

    /**
     *  根据 shopId 查询 所有订单
     * @param id
     * @return
     */
    List<OrderMaster> findByShopIdAndStatus(Integer id,int status);

    /**
     *  更改商品的状态
     * @param id
     * @return
     */
    ResultEnum modifyOrderStatus(Integer id,int status);

    List<OrderMaster> findByShopIdAndPayStatus(Integer is,int payStatus);
}
