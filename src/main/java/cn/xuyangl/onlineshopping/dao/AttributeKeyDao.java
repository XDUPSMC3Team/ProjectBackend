package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Attributekey;
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
public interface AttributeKeyDao extends JpaRepository<Attributekey,Integer>{

    /**
     *  根据 categoryId 查找所有的 AttributeKey
     * @param id
     * @return
     */
    List<Attributekey> findAllByCategoryId(Integer id);
}
