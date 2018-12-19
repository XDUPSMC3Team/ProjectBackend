package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.AttributeValueDao;
import cn.xuyangl.onlineshopping.entity.AttributeValue;
import cn.xuyangl.onlineshopping.service.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 16:52
 */
@Service
public class AttributeValueServiceImpl implements AttributeValueService{

    @Autowired
    private AttributeValueDao attributeValueDao;

    /**
     *  增加一个 AttributeValue
     * @param attributeValue
     */
    @Override
    public void addAttributeValue(AttributeValue attributeValue) {
        attributeValueDao.save(attributeValue);
    }

    /**
     *  根据 attributeKey 来查找所有的AttributeValue
     * @param id
     * @return
     */
    @Override
    public List<AttributeValue> findByAttributeKey(Integer id) {
        return attributeValueDao.findAllByAttributeKeyId(id);
    }

    @Override
    public void delelteByAttributeKey(Integer id) {
        attributeValueDao.deleteAllByAttributeKeyId(id);
    }
}
