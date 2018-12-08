package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
