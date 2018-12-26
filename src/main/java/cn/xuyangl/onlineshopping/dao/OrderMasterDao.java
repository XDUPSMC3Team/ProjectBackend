package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterDao extends JpaRepository<OrderMaster, Integer> {
    OrderMaster findById(Integer id);
    List<OrderMaster> findByShopIdAndStatus(Integer id,int status);
    List<OrderMaster> findAllByShopIdAndPayStatusAndStatusLessThan(int shopId,int payStatus,int status);
    List<OrderMaster> findAllByBuyerId(Integer buyerId);
}
