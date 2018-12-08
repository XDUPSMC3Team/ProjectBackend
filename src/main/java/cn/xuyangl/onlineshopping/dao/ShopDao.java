package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 14:33
 */
@Repository
public interface ShopDao extends JpaRepository<Shop,Integer>{
    List<Shop> findByStatus(Integer status);

    Shop findById(Integer id);
}
