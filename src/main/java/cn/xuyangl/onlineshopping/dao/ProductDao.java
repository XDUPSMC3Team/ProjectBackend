package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    List<Product> findAllByIdIn(List<Integer> productIds);

    List<Product> findAllByShopIdAndStatus(Integer shopId, Integer status);

    Product findById(Integer id);

    Page<Product> findAllByCategoryIdAndStatus(Integer categoryId, Integer status, Pageable pageable);

    @Query("update Product as p set p.adMoney = :adFee where p.id = :productId")
    @Transactional
    @Modifying
    void addAdFeeForProduct(@Param("productId")int productId,@Param("adFee")double adFee);

    @Query(nativeQuery = true, value = "SELECT * FROM T_PRODUCT ORDER BY adMoney DESC LIMIT 0,10")
    @Transactional
    List<Product> findTop10BiddingProducts();
}
