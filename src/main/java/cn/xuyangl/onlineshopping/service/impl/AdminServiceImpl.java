package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.AdminDAO;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.Admin;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;
    private final ShopDao shopDAO;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO, ShopDao shopDAO) {
        this.adminDAO = adminDAO;
        this.shopDAO = shopDAO;
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
}
