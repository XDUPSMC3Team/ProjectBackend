package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.BuyerOrderDetailVO;
import cn.xuyangl.onlineshopping.VO.BuyerOrderVO;
import cn.xuyangl.onlineshopping.dao.*;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.model.SimpleOrderForm;
import cn.xuyangl.onlineshopping.service.BuyerOrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjin
 * created on 2018/12/20 9:38 AM
 */
@Service
public class BuyerOrderServiceImpl implements BuyerOrderService {

    private ProductDao productDao;
    private ProductSpecsDao productSpecsDao;
    private OrderMasterDao orderMasterDao;
    private OrderDetailDao orderDetailDao;
    private BuyerDao buyerDao;
    private ShopDao shopDao;

    public BuyerOrderServiceImpl(ProductDao productDao,
                                 ProductSpecsDao productSpecsDao,
                                 OrderMasterDao orderMasterDao,
                                 OrderDetailDao orderDetailDao,
                                 BuyerDao buyerDao,
                                 ShopDao shopDao
                                 ) {
        this.productDao = productDao;
        this.productSpecsDao = productSpecsDao;
        this.orderMasterDao = orderMasterDao;
        this.orderDetailDao = orderDetailDao;
        this.buyerDao = buyerDao;
        this.shopDao = shopDao;
    }

    @Override
    public Integer simpleOrder(SimpleOrderForm sof, Integer buyerId) {
        // 减库存
        ProductSpecs ps = productSpecsDao.findById(sof.getSpecsId());
        ps.setStock(ps.getStock() - sof.getAmount());
        productSpecsDao.saveAndFlush(ps);

        // 生成订单master部分
        Integer productId = ps.getProductId();
        Double price = ps.getPrice();
        String detail = ps.getDetail();
        Double money = price * sof.getAmount();
        Product product = productDao.findById(productId);
        Integer shopId = product.getShopId();
        OrderMaster om = new OrderMaster();
        om.setBuyerId(buyerId);
        om.setShopId(shopId);
        om.setMoney(money);
        Buyer buyer = buyerDao.findById(buyerId);
        om.setReceiverName(buyer.getRealName());
        om.setAddress(buyer.getAddress());
        om.setCreateTime(LocalDateTime.now());
        om.setUpdateTime(LocalDateTime.now());
        Integer masterId = orderMasterDao.saveAndFlush(om).getId();

        // 生成订单detail部分
        OrderDetail od = new OrderDetail();
        od.setProductName(product.getName());
        od.setMasterId(masterId);
        od.setProductId(productId);
        od.setSpecsId(sof.getSpecsId());
        od.setPrice(price);
        od.setAmount(sof.getAmount());
        od.setCreateTime(LocalDateTime.now());
        od.setUpdateTime(LocalDateTime.now());
        orderDetailDao.saveAndFlush(od);

        return masterId;
    }

    @Override
    public List<BuyerOrderVO> getOrderList(Integer buyerId, Integer page, Integer size) {
        List<OrderMaster> orderMasters = orderMasterDao.findAllByBuyerId(buyerId);
        List<BuyerOrderVO> buyerOrderVOS = new ArrayList<>();
        for (OrderMaster om : orderMasters) {
            BuyerOrderVO buyerOrderVO = new BuyerOrderVO();
            BeanUtils.copyProperties(om, buyerOrderVO);
            buyerOrderVO.setShopName(shopDao.findById(om.getShopId()).getShopName());
            List<OrderDetail> orderDetails = orderDetailDao.findAllByMasterId(om.getId());
            List<BuyerOrderDetailVO> buyerOrderDetailVOS = new ArrayList<>();
            for (OrderDetail od : orderDetails) {
                BuyerOrderDetailVO buyerOrderDetailVO = new BuyerOrderDetailVO();
                BeanUtils.copyProperties(od, buyerOrderDetailVO);
                buyerOrderDetailVOS.add(buyerOrderDetailVO);
            }
            buyerOrderVO.setOrderDetailList(buyerOrderDetailVOS);
            buyerOrderVOS.add(buyerOrderVO);
        }
        return buyerOrderVOS;
    }

    @Override
    public BuyerOrderVO getOrder(Integer orderId) {
        OrderMaster om = orderMasterDao.findById(orderId);
        BuyerOrderVO buyerOrderVO = new BuyerOrderVO();
        BeanUtils.copyProperties(om, buyerOrderVO);
        buyerOrderVO.setShopName(shopDao.findById(om.getShopId()).getShopName());
        List<OrderDetail> orderDetails = orderDetailDao.findAllByMasterId(om.getId());
        List<BuyerOrderDetailVO> buyerOrderDetailVOS = new ArrayList<>();
        for (OrderDetail od : orderDetails) {
            BuyerOrderDetailVO buyerOrderDetailVO = new BuyerOrderDetailVO();
            BeanUtils.copyProperties(od, buyerOrderDetailVO);
            buyerOrderDetailVOS.add(buyerOrderDetailVO);
        }
        buyerOrderVO.setOrderDetailList(buyerOrderDetailVOS);
        return buyerOrderVO;
    }

    @Override
    public void cartOrder() {

    }

    @Override
    public Boolean checkNameAndAddress(Integer buyerId) {
        Buyer buyer = buyerDao.findById(buyerId);
        return  !(buyer.getRealName() == null || buyer.getRealName().equals("") ||
                buyer.getAddress() == null || buyer.getAddress().equals(""));
    }
}
