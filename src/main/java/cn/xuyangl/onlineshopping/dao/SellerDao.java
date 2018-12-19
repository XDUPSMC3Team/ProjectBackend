package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description shop owner dao
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 10:38
 */
public interface SellerDao extends JpaRepository<Seller,Integer>{

    /**
     *  根据邮箱查询seller
     * @param email
     * @return
     */
    Seller findSellerByEmailOrUsername(String email,String username);


    /**
     *  根据用户查找该用户
     * @param username
     * @return
     */
    Seller findSellerByUsername(String username);

    /**
     *  根据邮箱查找该用户
     * @param email
     * @return
     */
    Seller findSellerByEmail(String email);

    Seller findSellerById(Integer id);
}
