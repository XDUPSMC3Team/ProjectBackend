package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.OrderVO;
import cn.xuyangl.onlineshopping.dao.AdminDAO;
import cn.xuyangl.onlineshopping.dao.OrderDetailDAO;
import cn.xuyangl.onlineshopping.dao.OrderMasterDAO;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.Admin;
import cn.xuyangl.onlineshopping.entity.OrderDetail;
import cn.xuyangl.onlineshopping.entity.OrderMaster;
import cn.xuyangl.onlineshopping.entity.Shop;
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

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO, ShopDao shopDAO, OrderMasterDAO orderMasterDAO, OrderDetailDAO orderDetailDAO) {
        this.adminDAO = adminDAO;
        this.shopDAO = shopDAO;
        this.orderMasterDAO = orderMasterDAO;
        this.orderDetailDAO = orderDetailDAO;
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
}
