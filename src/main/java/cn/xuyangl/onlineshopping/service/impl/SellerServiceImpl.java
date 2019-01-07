package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.VO.StatusEnum;
import cn.xuyangl.onlineshopping.dao.BuyerDao;
import cn.xuyangl.onlineshopping.dao.SellerDao;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.model.IncomeHistoryData;
import cn.xuyangl.onlineshopping.model.OrderData;
import cn.xuyangl.onlineshopping.model.OrderDetailData;
import cn.xuyangl.onlineshopping.model.SaleHistoryData;
import cn.xuyangl.onlineshopping.service.BuyerService;
import cn.xuyangl.onlineshopping.service.OrderDetailService;
import cn.xuyangl.onlineshopping.service.OrderMasterService;
import cn.xuyangl.onlineshopping.service.SellerService;
import cn.xuyangl.onlineshopping.utils.MailUtil;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 10:43
 */
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private BuyerService buyerService;

    /**
     *  seller 注册
     * @param seller
     * @return
     */
    @Override
    public Result register(Seller seller) {

        Seller sellerByUsername = sellerDao.findSellerByUsername(seller.getUsername());
        if (sellerByUsername!=null)
        {
            return ResultUtil.error(ResultEnum.RegisterUsernameAlreadyExist);
        }
        if (sellerDao.findSellerByEmail(seller.getEmail())!=null)
        {
            return ResultUtil.error(ResultEnum.RegisterEmailAlreadyExist);
        }
        // 补充默认属性的值
        seller.setStatus(0);    // 表示为正常用户
        seller.setCreateTime(LocalDateTime.now());
        // 查找用户是否存在
        sellerDao.save(seller);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage register_success = MailUtil.createMessage(seller.getEmail(), "【注册成功】", "Congratulations\n. You have successfully registered us. Have a shopping experience.");
                javaMailSender.send(register_success);
            }
        }).start();
        return ResultUtil.success();
    }

    @Override
    public Seller findByEmail(String username) {
        Seller sellerByEmail = sellerDao.findSellerByEmailOrUsername(username,username);
        return sellerByEmail;
    }

    @Override
    public ResultEnum registerShop(Shop shop) {
        /**
         *  首先查找该seller 是否已经有shop
         *
         */
        Integer sellerId = shop.getSellerId();
        Shop shopBySellerId = shopDao.findShopBySellerId(sellerId);
        // 如果商铺存在，并且正常状态
        if (shopBySellerId!=null&&(shopBySellerId.getStatus()==1||shopBySellerId.getStatus()==0))
        {
            return  ResultEnum.OneSellerOnlyHasOneShop;
        }
        // 补全属性
        shop.setStatus(0);  // 设置为受理中
        shop.setCreateTime(LocalDateTime.now());
        shop.setUpdateTime(LocalDateTime.now());
        shop.setAccount(0.0);
        try{
            shopDao.save(shop);
            return ResultEnum.Success;
        }catch (Exception e)
        {
            return ResultEnum.RegisterError;
        }
    }

    /**
     *  根据 shopID 查询商家的所有销售历史
     * @param shopId
     * @return
     */

    @Override
    public Result findSaleHistory(Integer shopId) {

        // initial
        List<OrderData> orderDataList = new ArrayList<>();
        // 查询  OrderMaster
        List<OrderMaster> byShopId = orderMasterService.findByShopIdAndStatus(shopId,3);
        if (byShopId==null)
        {
            return  ResultUtil.error(ResultEnum.SHOP_NOT_FOUND);
        }
//        for (OrderMaster orderMaster: byShopId)
//        {
//            OrderData orderData  = new OrderData();
//            BeanUtils.copyProperties(orderMaster,orderData);
//            List<OrderDetail> orderDetailByOrderMasterId = orderDetailService.findOrderDetailByOrderMasterId(orderMaster.getId());
//            for (OrderDetail orderDetail:orderDetailByOrderMasterId)
//            {
//                OrderDetailData orderDetailData = new OrderDetailData();
//                BeanUtils.copyProperties(orderDetail,orderDetailData);
//                orderData.getOrderDetailDataList().add(orderDetailData);
//            }
//            orderDataList.add(orderData);
//        }
//        return ResultUtil.success(orderDataList);
        return ResultUtil.success(fillData(shopId,byShopId,orderDataList));
    }

    /**
     *  查询所有的已付款商品信息
     * @param shopId
     * @return
     */

    @Override
    public Result findAllPayedOrder(Integer shopId)
    {
        // initial
        List<OrderData> orderDataList = new ArrayList<>();
        // 查询  OrderMaster
        List<OrderMaster> byShopId = orderMasterService.findByShopIdAndPayStatus(shopId,1);
        if (byShopId==null)
        {
            return  ResultUtil.error(ResultEnum.SHOP_NOT_FOUND);
        }
       return ResultUtil.success(fillData(shopId,byShopId,orderDataList));
    }

    public List<OrderData> fillData(int shopId,List<OrderMaster> byShopId,List<OrderData> orderDataList)
    {
        Shop one = shopDao.findOne(shopId);
        for (OrderMaster orderMaster: byShopId)
        {
            OrderData orderData  = new OrderData();
            // 添加shopName字段
            orderData.setShopName(one.getShopName());
            Integer buyerId = orderMaster.getBuyerId();
            Buyer byId = buyerService.findById(buyerId);
            // 添加address 字段
            orderData.setAddress(byId.getAddress());
            BeanUtils.copyProperties(orderMaster,orderData);
            List<OrderDetail> orderDetailByOrderMasterId = orderDetailService.findOrderDetailByOrderMasterId(orderMaster.getId());
            for (OrderDetail orderDetail:orderDetailByOrderMasterId)
            {
                OrderDetailData orderDetailData = new OrderDetailData();
                BeanUtils.copyProperties(orderDetail,orderDetailData);
                orderData.getOrderDetailDataList().add(orderDetailData);
            }
            orderDataList.add(orderData);
        }
        return orderDataList;
    }

    /**
     *  查询某年某月某日某周的历史销售记录
     * @param shopId
     * @param date
     * @return
     */
    @Override
    public Result findSaleHistoryByDate(Integer shopId, Date date) {
        List<List<OrderMaster>> saleHistoryByDate = orderMasterService.findSaleHistoryByDate(shopId, StatusEnum.Received.code, date);
        SaleHistoryData saleHistoryData = new SaleHistoryData();
        saleHistoryData.setDaily(fillData(shopId,saleHistoryByDate.get(0),new ArrayList<>()));
        saleHistoryData.setWeekly(fillData(shopId,saleHistoryByDate.get(1),new ArrayList<>()));
        saleHistoryData.setMonthly(fillData(shopId,saleHistoryByDate.get(2),new ArrayList<>()));
        saleHistoryData.setYearly(fillData(shopId,saleHistoryByDate.get(3),new ArrayList<>()));
        return ResultUtil.success(saleHistoryData);
    }

    @Override
    public Result findIncomeHistoryByDate(Integer shopId, Date date) {
        System.out.println(StatusEnum.Received.code);
        List<List<OrderMaster>> saleHistoryByDate = orderMasterService.findSaleHistoryByDate(shopId, StatusEnum.Received.code, date);

        IncomeHistoryData incomeHistoryData = new IncomeHistoryData();
        if (saleHistoryByDate.get(0)!=null&&saleHistoryByDate.get(0).size()>0)
        {
            incomeHistoryData.setDaily( saleHistoryByDate.get(0).stream().map(OrderMaster::getMoney).reduce(Double::sum).get());
        }
        if (saleHistoryByDate.get(1)!=null&&saleHistoryByDate.get(1).size()>0)
        {
            incomeHistoryData.setWeekly( saleHistoryByDate.get(1).stream().map(OrderMaster::getMoney).reduce(Double::sum).get());
        }
        if (saleHistoryByDate.get(2)!=null&&saleHistoryByDate.get(2).size()>0)
        {
            incomeHistoryData.setMonthly( saleHistoryByDate.get(2).stream().map(OrderMaster::getMoney).reduce(Double::sum).get());
        }
        if (saleHistoryByDate.get(3)!=null&&saleHistoryByDate.get(3).size()>0)
        {
            incomeHistoryData.setYearly( saleHistoryByDate.get(3).stream().map(OrderMaster::getMoney).reduce(Double::sum).get());
        }
        if (saleHistoryByDate.get(4)!=null&&saleHistoryByDate.get(4).size()>0)
        {
            incomeHistoryData.setAll( saleHistoryByDate.get(4).stream().map(OrderMaster::getMoney).reduce(Double::sum).get());
        }
        return ResultUtil.success(incomeHistoryData);
    }

    /**
     *  根据shopId 查询账户余额
     * @param shopId
     * @return
     */
    @Override
    public Result findAccountByShopId(Integer shopId) {
        Shop one = shopDao.findOne(shopId);
        if (one==null)
        {
            return ResultUtil.error(ResultEnum.SHOP_NOT_FOUND);
        }
        return ResultUtil.success(one.getAccount());
    }
}
