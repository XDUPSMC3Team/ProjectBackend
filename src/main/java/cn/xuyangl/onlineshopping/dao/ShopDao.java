package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Shop;
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
 * @Date: 2018/12/1 14:33
 */
@Repository
public interface ShopDao extends JpaRepository<Shop,Integer>{
    List<Shop> findByStatus(Integer status);

    Shop findById(Integer id);

    List<Shop> findAllByIdIn(List<Integer> shopIds);

    Shop findShopBySellerId(int sellerId);

    List<Shop> findByShopNameContaining(String keyword);

    @Query(value = "update Shop as s set s.adMoney = :money where s.id = :shopId")
    @Transactional
    @Modifying
    void addAdmoney(@Param("shopId")Integer shopId,@Param("money")double money);

    // 最高的5个商店
    @Query(nativeQuery = true, value = "SELECT s FROM T_SHOP s ORDER BY s.adMoney DESC LIMIT 5")
    @Transactional
    List<Shop> findTop5BiddingShops();
}
