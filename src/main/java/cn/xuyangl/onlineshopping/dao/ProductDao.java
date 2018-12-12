package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 14:54
 */
@Repository
public interface ProductDao extends JpaRepository<Product,Integer>{


    Page<Product> findAllByStatus(Integer status, Pageable pageable);

    Page<Product> findAllByNameContainingAndStatus(String keyWord, Integer status, Pageable pageable);

    Page<Product> findAllByIdIn(List<Integer> productIds, Pageable pageable);

    List<Product> findAllByShopIdAndStatus(Integer shopId, Integer status);
}
