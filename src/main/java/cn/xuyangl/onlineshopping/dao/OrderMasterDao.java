package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.OrderMaster;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderMasterDao extends JpaRepository<OrderMaster, Integer> {
    OrderMaster findById(Integer id);
    List<OrderMaster> findByShopIdAndStatus(Integer id,int status);
    List<OrderMaster> findByShopIdAndStatusGreaterThanEqual(Integer id,int status);
    List<OrderMaster> findAllByShopIdAndPayStatusAndStatusLessThan(int shopId,int payStatus,int status);
    List<OrderMaster> findAllByBuyerId(Integer buyerId);
    List<OrderMaster> findAllByShopIdAndStatusAndReceiveTimeBetween(int shopId, int status, LocalDateTime start, LocalDateTime end);
    List<OrderMaster> findAllByBuyerIdAndCreateTimeBetween(Integer buyerId, LocalDateTime start, LocalDateTime end);

    List<OrderMaster> findAllByIdIn(List<Integer> ids);
    List<OrderMaster> findAllByStatus(int status);
}
