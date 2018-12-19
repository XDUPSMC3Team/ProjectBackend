package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.OrderDetailDAO;
import cn.xuyangl.onlineshopping.entity.OrderDetail;
import cn.xuyangl.onlineshopping.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/19 15:02
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    private OrderDetailDAO orderDetailDAO;


    /**
     *  根据 orderMasteriD 查询所有的 OrderDetail
     * @param orderMasterId
     * @return
     */
    @Override
    public List<OrderDetail> findOrderDetailByOrderMasterId(Integer orderMasterId) {
        return orderDetailDAO.findAllByMasterId(orderMasterId);
    }
}
