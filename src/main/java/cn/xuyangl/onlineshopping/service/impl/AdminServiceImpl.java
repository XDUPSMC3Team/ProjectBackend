package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.BiddingInfoVO;
import cn.xuyangl.onlineshopping.VO.BuyerOrderDetailVO;
import cn.xuyangl.onlineshopping.VO.BuyerOrderVO;
import cn.xuyangl.onlineshopping.VO.OrderVO;
import cn.xuyangl.onlineshopping.dao.*;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final ShopDao shopDAO;
    private final OrderMasterDao orderMasterDAO;
    private final OrderDetailDao orderDetailDao;
    private final BuyerDao buyerDao;
    private final ProductDao productDao;
    private final SellerDao sellerDao;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, ShopDao shopDAO, OrderMasterDao orderMasterDAO, OrderDetailDao orderDetailDao, BuyerDao buyerDao, ProductDao productDao, SellerDao sellerDao) {
        this.adminDao = adminDao;
        this.shopDAO = shopDAO;
        this.orderMasterDAO = orderMasterDAO;
        this.orderDetailDao = orderDetailDao;
        this.buyerDao = buyerDao;
        this.productDao = productDao;
        this.sellerDao = sellerDao;
    }

    @Override
    public Boolean register(Admin admin) {
        if (adminDao.findByUsername(admin.getUsername()) != null) {
            return false;
        }
        adminDao.saveAndFlush(admin);
        return true;
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin = adminDao.findByUsername(username);
        if (admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    @Override
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    @Override
    public List<Shop> newAppliedShops() {
        return shopDAO.findByStatus(0);
    }

    @Override
    public Boolean approveShop(Integer shopId) {
        Shop shop = shopDAO.findById(shopId);
        if (shop == null) {
            return false;
        }
        shop.setStatus(1);
        shopDAO.saveAndFlush(shop);
        return true;
    }

    @Override
    public Boolean rejectShop(Integer shopId) {
        Shop shop = shopDAO.findById(shopId);
        if (shop == null) {
            return false;
        }
        shop.setStatus(2);
        shopDAO.saveAndFlush(shop);
        return true;
    }

    @Override
    public Shop findShopById(Integer shopId) {
        return shopDAO.findById(shopId);
    }

    @Override
    public List<Shop> findShopByName(String name) {
        return shopDAO.findByShopNameContaining(name);
    }

    @Override
    public Boolean closeShop(Integer shopId) {
        Shop shop = shopDAO.findById(shopId);
        if (shop == null) {
            return false;
        }
        shop.setStatus(3);
        shopDAO.saveAndFlush(shop);
        return true;
    }

    @Override
    public OrderVO findOrderById(Integer id) {
        OrderMaster om = orderMasterDAO.findById(id);
        OrderVO buyerOrderVO = new OrderVO();
        BeanUtils.copyProperties(om, buyerOrderVO);
        buyerOrderVO.setShopName(shopDAO.findById(om.getShopId()).getShopName());
        List<OrderDetail> orderDetails = orderDetailDao.findAllByMasterId(om.getId());
        List<BuyerOrderDetailVO> buyerOrderDetailVOS = new ArrayList<>();
        for (OrderDetail od : orderDetails) {
            BuyerOrderDetailVO buyerOrderDetailVO = new BuyerOrderDetailVO();
            BeanUtils.copyProperties(od, buyerOrderDetailVO);
            buyerOrderDetailVOS.add(buyerOrderDetailVO);
        }
        buyerOrderVO.setOrderDetailDataList(buyerOrderDetailVOS);
        return buyerOrderVO;
    }

    @Override
    public Buyer findCustomerByUsername(String username) {
        return buyerDao.findByUsername(username);
    }

    @Override
    public Boolean offShelves(Integer productId) {
        Product product = productDao.findById(productId);
        if (product == null) {
            return false;
        }
        product.setStatus(1);
        productDao.saveAndFlush(product);
        return true;
    }

    @Override
    public Boolean blockUser(Integer userId) {
        Buyer buyer = buyerDao.findById(userId);
        if (buyer == null) {
            return false;
        }
        buyer.setStatus(1);
        buyerDao.saveAndFlush(buyer);
        return true;
    }

    @Override
    public Boolean blockSeller(Integer sellerId) {
        Seller seller = sellerDao.findSellerById(sellerId);
        if (seller == null) {
            return false;
        }
        seller.setStatus(1);
        sellerDao.saveAndFlush(seller);
        return true;
    }

    @Override
    public BiddingInfoVO biddingInfo() {
        BiddingInfoVO info = new BiddingInfoVO();
        info.setShops(shopDAO.findTop5BiddingShops());
        info.setProducts(productDao.findTop10BiddingProducts());
        return info;
    }
}
