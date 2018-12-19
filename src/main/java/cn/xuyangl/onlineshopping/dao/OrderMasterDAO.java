package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterDAO extends JpaRepository<OrderMaster, Integer> {
    OrderMaster findById(Integer id);
    List<OrderMaster> findByShopIdAAndStatus(Integer id,int status);
    List<OrderMaster> findAllByShopIdAndPayStatus(int shopId,int payStatus);
}
