package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.Attributekey;

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
    void addAttributeKey(Attributekey attributekey);

    /**
     *  根据分类 id 查找所有的 属性key
     * @param categoryId
     * @return
     */
    List<Attributekey> findByCategoryId(Integer categoryId);
}
