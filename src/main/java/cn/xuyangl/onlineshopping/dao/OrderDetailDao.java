package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {
    OrderDetail findByMasterId(Integer masterId);

    List<OrderDetail> findAllByMasterId(Integer masterId);

    OrderDetail findById(Integer id);

    List<OrderDetail> findAllByMasterIdAndProductNameContaining(Integer masterId, String key);
}
