package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.ShopCollect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xjin
 * created on 2018/12/10 2:46 PM
 */
public interface ShopCollectDao extends JpaRepository<ShopCollect, Integer> {
    List<ShopCollect> findAllByBuyerId(Integer buyerId);
}
