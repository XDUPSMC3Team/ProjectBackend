package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.ProductSpecsDao;
import cn.xuyangl.onlineshopping.entity.ProductSpecs;
import cn.xuyangl.onlineshopping.service.ProductService;
import cn.xuyangl.onlineshopping.service.ProductSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/7 09:18
 */

@Service
public class ProductSpecsServiceImpl implements ProductSpecsService{

    @Autowired
    private ProductSpecsDao productSpecsDao;


    /**
     *  增加一条商品详情信息
     * @param productSpecs
     */
    @Override
    public void addProductSpecs(ProductSpecs productSpecs) {

        // 补充其他字段
        productSpecs.setCreateTime(LocalDateTime.now());
        productSpecs.setUpdateTime(LocalDateTime.now());
        productSpecsDao.save(productSpecs);
    }


    /**
     *  查询所有的商品详情
     * @return
     */
    @Override
    public List<ProductSpecs> findAll() {
        return productSpecsDao.findAll();
    }
}
