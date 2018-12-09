package cn.xuyangl.onlineshopping.service;


import cn.xuyangl.onlineshopping.entity.Admin;
import cn.xuyangl.onlineshopping.entity.Shop;

import java.util.List;

public interface AdminService {
    Boolean register(Admin admin);

    Admin login(String username, String password);

    Admin findByUsername(String username);

    List<Shop> newAppliedShops();

    Boolean approveShop(Integer shopId);

    Boolean rejectShop(Integer shopId);

    Shop findShop(Integer shopId);

    Boolean closeShop(Integer shopId);
}
