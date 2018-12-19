package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xjin
 * created on 2018/12/19 2:22 PM
 */
public interface ShopCartDao extends JpaRepository<ShopCart, Integer> {
    ShopCart findBySpecsIdAndBuyerId(int specsId, int buyerId);

    List<ShopCart> findAllByBuyerId(int buyerId);
}
