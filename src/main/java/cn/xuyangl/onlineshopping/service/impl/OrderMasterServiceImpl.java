package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.OrderMasterDao;
import cn.xuyangl.onlineshopping.entity.OrderMaster;
import cn.xuyangl.onlineshopping.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/19 14:28
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService{

    @Autowired
    private OrderMasterDao orderMasterDAO;

    /**
     *  根据shopId 查询OrderMaster
     *  status 为3 表示为已收货
     * @param id
     * @return
     */
    @Override
    public List<OrderMaster> findByShopIdAndStatus(Integer id,int status) {
       return orderMasterDAO.findByShopIdAndStatus(id,status);
    }

    /**
     *  更改商品订单的状态
     * @param id
     * @return
     */
    @Override
    public ResultEnum modifyOrderStatus(Integer id,int status) {

        OrderMaster byId = orderMasterDAO.findById(id);
        if (byId==null)
        {
            return ResultEnum.OrderNotExist;
        }
        byId.setStatus(status);
        if (status>=3)
        {
            byId.setReceiveTime(LocalDateTime.now());
        }
        byId.setUpdateTime(LocalDateTime.now());
        orderMasterDAO.saveAndFlush(byId);
        return ResultEnum.Success;
    }

    /**
     *  查找所有已付款订单
     * @param shopId
     * @param payStatus
     * @return
     */

    @Override
    public List<OrderMaster> findByShopIdAndPayStatus(Integer shopId, int payStatus) {
        return orderMasterDAO.findAllByShopIdAndPayStatusAndStatusLessThan(shopId,payStatus,2);
    }
}
