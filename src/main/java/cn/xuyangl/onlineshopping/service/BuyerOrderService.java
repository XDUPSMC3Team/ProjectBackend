package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.BuyerOrderVO;
import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.model.CommentForm;
import cn.xuyangl.onlineshopping.model.SimpleOrderForm;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * @author xjin
 * created on 2018/12/20 9:37 AM
 */
public interface BuyerOrderService {
    // 直接下单
    Integer simpleOrder(SimpleOrderForm sof, Integer buyerId);

    // 购物车下单
    List<Integer> cartOrder(List<Integer> cartIdList, Integer buyerId);

    // 订单列表
    List<BuyerOrderVO> getOrderList(Integer buyerId, Integer page, Integer size);

    // 订单详情
    BuyerOrderVO getOrder(Integer orderId);

    // 检查是否填写真实姓名和地址
    Boolean checkNameAndAddress(Integer buyerId);

    // 支付订单
    void payOrder(Integer orderId);

    // 取消订单
    void cancelOrder(Integer orderId);

    // 确认收货
    void confirmOrder(Integer orderId);

    Result commentOrder(CommentForm commentForm, Integer buyerId);

    Result findOrderHistoryByDate(Integer buyerId, Date baseDate, int type);

    Result searchOrderByKeyWord(Integer buyerId, String key);
}
