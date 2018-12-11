package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.ProductCollect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xjin
 * created on 2018/12/10 2:45 PM
 */
public interface ProductCollectDao extends JpaRepository<ProductCollect, Integer> {

    List<ProductCollect> findAllByBuyerId(Integer buyerId);

    ProductCollect findByBuyerIdAndProductId(Integer buyerId, Integer productId);
}
