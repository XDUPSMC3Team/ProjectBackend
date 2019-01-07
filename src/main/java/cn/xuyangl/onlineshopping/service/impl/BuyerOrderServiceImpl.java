package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.*;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.dao.*;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.model.CommentForm;
import cn.xuyangl.onlineshopping.model.SimpleOrderForm;
import cn.xuyangl.onlineshopping.service.BuyerOrderService;
import cn.xuyangl.onlineshopping.service.OrderMasterService;
import cn.xuyangl.onlineshopping.utils.DateUtil;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private ShopCartDao shopCartDao;
    private CommentDao commentDao;

    public BuyerOrderServiceImpl(ProductDao productDao,
                                 ProductSpecsDao productSpecsDao,
                                 OrderMasterDao orderMasterDao,
                                 OrderDetailDao orderDetailDao,
                                 BuyerDao buyerDao,
                                 ShopDao shopDao,
                                 ShopCartDao shopCartDao,
                                 CommentDao commentDao
                                 ) {
        this.productDao = productDao;
        this.productSpecsDao = productSpecsDao;
        this.orderMasterDao = orderMasterDao;
        this.orderDetailDao = orderDetailDao;
        this.buyerDao = buyerDao;
        this.shopDao = shopDao;
        this.shopCartDao = shopCartDao;
        this.commentDao = commentDao;
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
    public List<Integer> cartOrder(List<Integer> cartIdList, Integer buyerId) {
        Map<Integer, List<SimpleOrderForm>> map = new HashMap<>();
        // 初始化要返回的masterId List
        List<Integer> masterIds = new ArrayList<>();
        // 1. 从购物车获取信息并结构化
        for (Integer cartId : cartIdList) {
            ShopCart sc = shopCartDao.findOne(cartId);
            if (!sc.getBuyerId().equals(buyerId)) return null;
            Integer shopId = productDao.findById(sc.getProductId()).getShopId();
            SimpleOrderForm sof = new SimpleOrderForm();
            if (map.containsKey(shopId)) {
                List<SimpleOrderForm> simpleOrderForms = map.get(shopId);
                sof.setSpecsId(sc.getSpecsId());
                sof.setAmount(sc.getAmount());
                simpleOrderForms.add(sof);
                map.put(shopId, simpleOrderForms);
            } else {
                List<SimpleOrderForm> simpleOrderForms = new ArrayList<>();
                sof.setSpecsId(sc.getSpecsId());
                sof.setAmount(sc.getAmount());
                simpleOrderForms.add(sof);
                map.put(shopId, simpleOrderForms);
            }
        }
        // 2. 生成订单并减库存
        for (Map.Entry<Integer, List<SimpleOrderForm>> entry : map.entrySet()) {
            Integer shopId = entry.getKey();
            List<SimpleOrderForm> sofs = entry.getValue();
            // 生成order_master
            OrderMaster om = new OrderMaster();
            om.setBuyerId(buyerId);
            om.setShopId(shopId);
            Buyer buyer = buyerDao.findById(buyerId);
            om.setReceiverName(buyer.getRealName());
            om.setAddress(buyer.getAddress());
            Integer masterId = orderMasterDao.saveAndFlush(om).getId();
            masterIds.add(masterId);
            // 生成order_detail
            Double sumMoney = 0.00;
            for (SimpleOrderForm sof : sofs) {
                ProductSpecs ps = productSpecsDao.findById(sof.getSpecsId());
                ps.setStock(ps.getStock() - sof.getAmount());
                Double price = ps.getPrice();
                // 累加总价
                sumMoney += price * sof.getAmount();
                productSpecsDao.saveAndFlush(ps);
                Integer productId = ps.getProductId();
                Product product = productDao.findById(productId);
                OrderDetail od = new OrderDetail();
                od.setProductId(productId);
                od.setProductName(product.getName());
                od.setMasterId(masterId);
                od.setSpecsId(sof.getSpecsId());
                od.setPrice(ps.getPrice());
                od.setAmount(sof.getAmount());
                od.setCreateTime(LocalDateTime.now());
                od.setUpdateTime(LocalDateTime.now());
                orderDetailDao.saveAndFlush(od);
            }
            om.setMoney(sumMoney);
            om.setCreateTime(LocalDateTime.now());
            om.setUpdateTime(LocalDateTime.now());
            orderMasterDao.saveAndFlush(om);
        }

        // 3. 从购物车移除
        shopCartDao.deleteAllByIdIn(cartIdList);
        return masterIds;
    }

    @Override
    public List<BuyerOrderVO> getOrderList(Integer buyerId, Integer page, Integer size) {
        List<OrderMaster> orderMasters = orderMasterDao.findAllByBuyerId(buyerId);
        return orderMastersToBuyerOrderVO(orderMasters);
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
            buyerOrderDetailVO.setOrderDetailId(od.getId());
            Comment comment = commentDao.findByOrderDetailId(od.getId());
            if (comment != null) {
                buyerOrderDetailVO.setCommentContent(comment.getContent());
            }
            buyerOrderDetailVOS.add(buyerOrderDetailVO);
        }
        buyerOrderVO.setOrderDetailList(buyerOrderDetailVOS);
        return buyerOrderVO;
    }

    @Override
    public Boolean checkNameAndAddress(Integer buyerId) {
        Buyer buyer = buyerDao.findById(buyerId);
        return  !(buyer.getRealName() == null || buyer.getRealName().equals("") ||
                buyer.getAddress() == null || buyer.getAddress().equals(""));
    }

    @Override
    public void  payOrder(Integer orderId) {
        OrderMaster om = orderMasterDao.findById(orderId);
        om.setPayStatus(1);
        /**
         *  给shop 账户增加相关money
         */
        shopDao.addAccount(om.getShopId(),om.getMoney());
        om.setUpdateTime(LocalDateTime.now());
        orderMasterDao.saveAndFlush(om);
    }

    @Override
    public void cancelOrder(Integer orderId) {
        OrderMaster om = orderMasterDao.findById(orderId);
        om.setStatus(-1);
        om.setUpdateTime(LocalDateTime.now());
        orderMasterDao.saveAndFlush(om);
    }

    @Override
    public void confirmOrder(Integer orderId) {
        OrderMaster om = orderMasterDao.findById(orderId);
        om.setStatus(StatusEnum.Received.code);
        om.setUpdateTime(LocalDateTime.now());
        orderMasterDao.saveAndFlush(om);
    }

    @Override
    public Result commentOrder(CommentForm commentForm, Integer buyerId) {
        OrderDetail od = orderDetailDao.findById(commentForm.getOrderDetailId());
        OrderMaster om = orderMasterDao.findById(od.getMasterId());
        Comment co = commentDao.findByBuyerIdAndOrderDetailId(buyerId, commentForm.getOrderDetailId());
        if (co != null) return ResultUtil.error(ResultEnum.RepeatedComment);
        if (om.getStatus() < 3) return ResultUtil.error(ResultEnum.OrderNotReceived);
        if (!om.getBuyerId().equals(buyerId)) return ResultUtil.error(ResultEnum.NO_RIGHT);
        Comment comment = new Comment();
        comment.setBuyerId(buyerId);
        comment.setProductId(od.getProductId());
        comment.setOrderMasterId(od.getMasterId());
        comment.setOrderDetailId(od.getId());
        comment.setContent(commentForm.getContent());
        commentDao.saveAndFlush(comment);
        // 复杂设置
        List<OrderDetail> orderDetails = orderDetailDao.findAllByMasterId(od.getMasterId());
        Integer allComment = 1;
        for (OrderDetail orderDetail : orderDetails) {
            Comment comment1 = commentDao.findByBuyerIdAndOrderDetailId(buyerId, od.getId());
            if (comment1 == null) {
                allComment = 0;
            }
        }
        if (allComment == 1) {
            om.setStatus(StatusEnum.Reviewed.code);
        }
        orderMasterDao.saveAndFlush(om);

        return ResultUtil.success();
    }

    @Override
    public Result findOrderHistoryByDate(Integer buyerId, Date date, int type) {
        LocalDateTime startTime = LocalDateTime.MIN;
        LocalDateTime endTime = LocalDateTime.MAX;
        switch (type) {
            case 1:
                //查询日
                startTime = DateUtil.getDateStartTime(date);
                endTime = DateUtil.getDateEndTime(date);
                break;
            case 2:
                //查询周
                startTime = DateUtil.getBeginDayOfWeek(date);
                endTime = DateUtil.getEndDayOfWeek(date);
                break;
            case 3:
                //查询月
                startTime = DateUtil.getBeginDayOfMonth(date);
                endTime = DateUtil.getEndDayOfMonth(date);
                break;
            case 4:
                //查询年
                startTime = DateUtil.getStartDayOfYear(date);
                endTime = DateUtil.getEndDayOfYear(date);
                break;
        }
        List<OrderMaster> orderMasters = orderMasterDao.findAllByBuyerIdAndCreateTimeBetween(buyerId, startTime, endTime);
        List<BuyerOrderVO> buyerOrderVOS = orderMastersToBuyerOrderVO(orderMasters);
        return ResultUtil.success(buyerOrderVOS);
    }

    @Override
    public Result searchOrderByKeyWord(Integer buyerId, String key) {
        List<OrderMaster> oms = orderMasterDao.findAllByBuyerId(buyerId);
        List<Integer> masterIds = new ArrayList<>();
        for (OrderMaster om : oms) {
            List<OrderDetail> orderDetails = orderDetailDao.findAllByMasterIdAndProductNameContaining(om.getId(), key);
            if (orderDetails.size() > 0) masterIds.add(om.getId());
        }
        List<OrderMaster> resultOms = orderMasterDao.findAllByIdIn(masterIds);
        List<BuyerOrderVO> buyerOrderVOS = orderMastersToBuyerOrderVO(resultOms);
        return ResultUtil.success(buyerOrderVOS);
    }

    List<BuyerOrderVO> orderMastersToBuyerOrderVO(List<OrderMaster> orderMasters) {
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
                buyerOrderDetailVO.setOrderDetailId(od.getId());
                Comment comment = commentDao.findByOrderDetailId(od.getId());
                if (comment != null) {
                    buyerOrderDetailVO.setCommentContent(comment.getContent());
                }
                buyerOrderDetailVOS.add(buyerOrderDetailVO);
            }
            buyerOrderVO.setOrderDetailList(buyerOrderDetailVOS);
            buyerOrderVOS.add(buyerOrderVO);
        }
        return buyerOrderVOS;
    }
}
