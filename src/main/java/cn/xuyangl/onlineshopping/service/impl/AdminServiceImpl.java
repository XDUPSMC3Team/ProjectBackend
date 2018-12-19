package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.OrderVO;
import cn.xuyangl.onlineshopping.dao.*;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;
    private final ShopDao shopDAO;
    private final OrderMasterDAO orderMasterDAO;
    private final OrderDetailDAO orderDetailDAO;
    private final BuyerDao buyerDao;
    private final ProductDao productDao;
    private final SellerDao sellerDao;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO, ShopDao shopDAO, OrderMasterDAO orderMasterDAO, OrderDetailDAO orderDetailDAO, BuyerDao buyerDao, ProductDao productDao, SellerDao sellerDao) {
        this.adminDAO = adminDAO;
        this.shopDAO = shopDAO;
        this.orderMasterDAO = orderMasterDAO;
        this.orderDetailDAO = orderDetailDAO;
        this.buyerDao = buyerDao;
        this.productDao = productDao;
        this.sellerDao = sellerDao;
    }

    @Override
    public Boolean register(Admin admin) {
        if (adminDAO.findByUsername(admin.getUsername()) != null) {
            return false;
        }
        adminDAO.saveAndFlush(admin);
        return true;
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin = adminDAO.findByUsername(username);
        if (admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    @Override
    public Admin findByUsername(String username) {
        return adminDAO.findByUsername(username);
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
        OrderMaster orderMaster = orderMasterDAO.findById(id);

        if (orderMaster == null) {
            return null;
        }

        OrderDetail orderDetail = orderDetailDAO.findByMasterId(id);

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderMaster, orderVO);

        if (orderDetail != null) {
            orderVO.setProductId(orderDetail.getProductId());
            orderVO.setProductName(orderDetail.getProductName());
            orderVO.setAmount(orderDetail.getAmount());
            orderVO.setPrice(orderDetail.getPrice());
        }
        return orderVO;
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
}
