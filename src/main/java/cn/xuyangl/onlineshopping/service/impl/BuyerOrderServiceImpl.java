package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.*;
import cn.xuyangl.onlineshopping.entity.OrderDetail;
import cn.xuyangl.onlineshopping.entity.OrderMaster;
import cn.xuyangl.onlineshopping.entity.Product;
import cn.xuyangl.onlineshopping.entity.ProductSpecs;
import cn.xuyangl.onlineshopping.model.SimpleOrderForm;
import cn.xuyangl.onlineshopping.service.BuyerOrderService;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public BuyerOrderServiceImpl(ProductDao productDao,
                                 ProductSpecsDao productSpecsDao,
                                 OrderMasterDao orderMasterDao,
                                 OrderDetailDao orderDetailDao,
                                 BuyerDao buyerDao
                                 ) {
        this.productDao = productDao;
        this.productSpecsDao = productSpecsDao;
        this.orderMasterDao = orderMasterDao;
        this.orderDetailDao = orderDetailDao;
        this.buyerDao = buyerDao;
    }

    @Override
    public void simpleOrder(SimpleOrderForm sof, Integer buyerId) {
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
        om.setReceiverName(buyerDao.findById(buyerId).getUsername());
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
    }

    @Override
    public void cartOrder() {

    }
}
