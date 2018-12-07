package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.ProductSpecs;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/7 09:17
 */
public interface ProductSpecsService {

    /**
     * 增加一个 商品详情记录
     * @param productSpecs
     */
    void addProductSpecs(ProductSpecs productSpecs);
}
