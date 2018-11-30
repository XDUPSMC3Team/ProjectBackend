package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xjin
 * created on 2018/11/30 3:42 PM
 */
public interface BuyerDao extends JpaRepository<Buyer, Integer> {
    Buyer findByUsername(String username);
}
