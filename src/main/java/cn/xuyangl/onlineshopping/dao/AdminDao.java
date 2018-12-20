package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
