package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.SellerDao;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.OrderDetail;
import cn.xuyangl.onlineshopping.entity.OrderMaster;
import cn.xuyangl.onlineshopping.entity.Seller;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.model.OrderData;
import cn.xuyangl.onlineshopping.model.OrderDetailData;
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
    public boolean registerShop(Shop shop) {
        // 补全属性
        shop.setStatus(0);  // 设置为受理中
        shop.setCreateTime(LocalDateTime.now());
        shop.setUpdateTime(LocalDateTime.now());
        try{
            shopDao.save(shop);
            return true;
        }catch (Exception e)
        {
            return false;
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
        return fillData(byShopId,orderDataList);
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
       return fillData(byShopId,orderDataList);
    }

    public Result fillData(List<OrderMaster> byShopId,List<OrderData> orderDataList)
    {
        for (OrderMaster orderMaster: byShopId)
        {
            OrderData orderData  = new OrderData();
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
        return ResultUtil.success(orderDataList);
    }
}
