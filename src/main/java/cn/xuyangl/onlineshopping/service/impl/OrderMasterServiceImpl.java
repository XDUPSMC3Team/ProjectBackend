package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.OrderMasterDao;
import cn.xuyangl.onlineshopping.entity.OrderMaster;
import cn.xuyangl.onlineshopping.service.OrderMasterService;
import cn.xuyangl.onlineshopping.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
       return orderMasterDAO.findByShopIdAndStatusGreaterThanEqual(id,status);
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
        return orderMasterDAO.findAllByShopIdAndPayStatusAndStatusLessThan(shopId,payStatus,3);
    }

    /**
     *  查询某年 某月某日 某周的历史销售记录
     * @param shopId
     * @param status
     * @param date
     * @return
     */
    @Override
    public List<List<OrderMaster>> findSaleHistoryByDate(Integer shopId, int status, Date date) {


        /**
         *  查询每日
         */
        // 获得起始时间
        LocalDateTime dateStartTime = DateUtil.getDateStartTime(date);
        LocalDateTime dateEndTime = DateUtil.getDateEndTime(date);
        List<OrderMaster> daily = orderMasterDAO.findAllByShopIdAndStatusGreaterThanEqualAndReceiveTimeBetween(shopId, status, dateStartTime, dateEndTime);

        /**
         *  查询每周
         */
        // 获得起始时间
        LocalDateTime weekStartTime = DateUtil.getBeginDayOfWeek(date);
        LocalDateTime weekEndTime = DateUtil.getEndDayOfWeek(date);
        List<OrderMaster> weekly = orderMasterDAO.findAllByShopIdAndStatusGreaterThanEqualAndReceiveTimeBetween(shopId, status, weekStartTime, weekEndTime);

        /**
         *  查询每月
         */
        // 获得起始时间
        LocalDateTime monthStartTime = DateUtil.getBeginDayOfWeek(date);
        LocalDateTime monthEndTime = DateUtil.getEndDayOfMonth(date);
        List<OrderMaster> monthly = orderMasterDAO.findAllByShopIdAndStatusGreaterThanEqualAndReceiveTimeBetween(shopId, status, monthStartTime, monthEndTime);

        /**
         *  查询每年
         */
        // 获得起始时间
        LocalDateTime yearStartTime = DateUtil.getStartDayOfYear(date);
        LocalDateTime yearEndTime = DateUtil.getEndDayOfYear(date);
        List<OrderMaster> yearly = orderMasterDAO.findAllByShopIdAndStatusGreaterThanEqualAndReceiveTimeBetween(shopId, status, yearStartTime, yearEndTime);

        List<OrderMaster> all = orderMasterDAO.findByShopIdAndStatusGreaterThanEqual(shopId, status);

        List<List<OrderMaster>> result = new ArrayList<>();
        result.add(daily);
        result.add(weekly);
        result.add(monthly);
        result.add(yearly);
        result.add(all);
        return result;
    }
}
