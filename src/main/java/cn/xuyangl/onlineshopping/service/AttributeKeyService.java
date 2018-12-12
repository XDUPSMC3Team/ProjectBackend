package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.AttributeKey;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 16:45
 */
public interface AttributeKeyService {

    /**
     *  增加一条 attributekey
     * @param attributekey
     */
    void addAttributeKey(AttributeKey attributekey);

    /**
     *  根据分类 id 查找所有的 属性key
     * @param categoryId
     * @return
     */
    List<AttributeKey> findByCategoryId(Integer categoryId);

    AttributeKey findByName(String name);
}
