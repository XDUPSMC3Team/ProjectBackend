package cn.xuyangl.onlineshopping.service;


import cn.xuyangl.onlineshopping.VO.BiddingInfoVO;
import cn.xuyangl.onlineshopping.VO.OrderVO;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.model.IncomeHistoryData;

import java.util.Date;
import java.util.List;

public interface AdminService {
    Boolean register(Admin admin);

    Admin login(String username, String password);

    Admin findByUsername(String username);

    List<Shop> newAppliedShops();

    Boolean approveShop(Integer shopId);

    Boolean rejectShop(Integer shopId);

    Shop findShopById(Integer shopId);

    List<Shop> findShopByName(String name);

    Boolean closeShop(Integer shopId);

    OrderVO findOrderById(Integer id);

    Buyer findCustomerByUsername(String username);

    Boolean offShelves(Integer productId);

    Boolean blockUser(Integer userId);

    Boolean blockSeller(Integer sellerId);

    BiddingInfoVO biddingInfo();

    List<OrderVO> saleHistory();

    String exchangeRate();

    boolean changeExchangeRate(String rate);

    IncomeHistoryData getIncomeData(Date date);

    String getBalance();

    boolean withdraw(int money, String alipayId, String adminName);

    List<WithdrawHistory> withdrawHistory();
}
