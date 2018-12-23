package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.AttributeKeyDao;
import cn.xuyangl.onlineshopping.entity.AttributeKey;
import cn.xuyangl.onlineshopping.service.AttributeKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 16:51
 */
@Service
public class AttributeKeyServiceImpl implements AttributeKeyService{

    @Autowired
    private AttributeKeyDao attributeKeyDao;

    /**
     * 保存一个attributKey
     * @param attributekey
     */
    @Override
    public void addAttributeKey(AttributeKey attributekey) {

        attributeKeyDao.save(attributekey);
    }

    /**
     *   保存一个 AttributeKey
     * @param categoryId
     * @return
     */
    @Override
    public List<AttributeKey> findByCategoryId(Integer categoryId) {
        List<AttributeKey> allByCategoryId = attributeKeyDao.findAllByCategoryId(categoryId);
        return allByCategoryId.stream().filter(attributeKey -> !attributeKey.getAttributeKey().equals("Payment")).collect(Collectors.toList());
    }

    /**
     *  根据 attributeKey 的名称查找 AttributeKey
     * @param name
     * @return
     */
    @Override
    public AttributeKey findByNameAndCategoryId(String name,Integer id) {
        return attributeKeyDao.findByAttributeKeyAndCategoryId(name,id);
    }
}


