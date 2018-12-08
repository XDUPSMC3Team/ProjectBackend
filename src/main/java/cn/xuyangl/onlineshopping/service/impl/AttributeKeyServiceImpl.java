package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.AttributeKeyDao;
import cn.xuyangl.onlineshopping.entity.Attributekey;
import cn.xuyangl.onlineshopping.service.AttributeKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void addAttributeKey(Attributekey attributekey) {

        attributeKeyDao.save(attributekey);
    }

    /**
     *   保存一个 AttributeKey
     * @param categoryId
     * @return
     */
    @Override
    public List<Attributekey> findByCategoryId(Integer categoryId) {
        return attributeKeyDao.findAllByCategoryId(categoryId);
    }
}
