package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.AttributeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 16:43
 */
@Repository
public interface AttributeKeyDao extends JpaRepository<AttributeKey,Integer>{

    /**
     *  根据 categoryId 查找所有的 AttributeKey
     * @param id
     * @return
     */
    List<AttributeKey> findAllByCategoryId(Integer id);

    AttributeKey findByAttributeKeyAndCategoryId(String attributeKey,Integer id);
}
