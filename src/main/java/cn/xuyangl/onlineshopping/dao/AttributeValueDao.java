package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 16:44
 */
public interface AttributeValueDao extends JpaRepository<AttributeValue,Integer>{

    /**
     *  根据attributekeyId 查找
     * @param id
     * @return
     */
    List<AttributeValue> findAllByAttributeKeyId(Integer id);
}
