package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExchangeRateDAO extends JpaRepository<ExchangeRate, Integer> {
    @Override
    List<ExchangeRate> findAll();
}
