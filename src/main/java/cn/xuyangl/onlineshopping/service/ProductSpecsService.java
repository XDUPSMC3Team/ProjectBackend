package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.ProductSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    /**
     *  查询所有的商品信息
     * @return
     */
    Page<ProductSpecs> findAll(int pageNum, int pageSize);

    /**
     *  根据productId  查询 productSpecs
     * @param productId
     * @return
     */
    List<ProductSpecs> findAllByProductId(String productId);

    ProductSpecs findProductsSpecs(String detail);
}
