package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.OrderDetail;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/19 15:01
 */

public interface OrderDetailService {

    List<OrderDetail> findOrderDetailByOrderMasterId(Integer orderMasterId);
}
