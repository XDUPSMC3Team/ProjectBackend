package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.WithdrawHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawHistoryDAO extends JpaRepository<WithdrawHistory, Integer> {
}
