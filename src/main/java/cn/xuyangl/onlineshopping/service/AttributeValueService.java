package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.AttributeValue;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 16:45
 */
public interface AttributeValueService {

    /**
     *  添加一条 attributeValue
     * @param attributeValue
     */
    void addAttributeValue(AttributeValue attributeValue);


    /**
     *  根据 attributekey 的 id 查找所有的 AttributeValue
     * @param id
     * @return
     */
    List<AttributeValue> findByAttributeKey(Integer id);
}
