package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {
    OrderDetail findByMasterId(Integer masterId);
}
