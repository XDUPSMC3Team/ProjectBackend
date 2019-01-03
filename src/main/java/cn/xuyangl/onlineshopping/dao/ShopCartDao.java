package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xjin
 * created on 2018/12/19 2:22 PM
 */
public interface ShopCartDao extends JpaRepository<ShopCart, Integer> {

    ShopCart findBySpecsIdAndBuyerId(int specsId, int buyerId);

    List<ShopCart> findAllByBuyerId(int buyerId);

    @Modifying
    @Transactional
    void deleteAllByIdIn(List<Integer> cartIdList);
}
