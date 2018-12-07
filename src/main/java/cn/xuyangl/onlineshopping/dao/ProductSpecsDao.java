package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.ProductSpecs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 17:07
 */
@Repository
public interface ProductSpecsDao extends JpaRepository<ProductSpecs,Integer>{
}
