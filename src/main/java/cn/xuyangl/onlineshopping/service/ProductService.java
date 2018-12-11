package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 15:39
 */
public interface ProductService {

    /**
     *  查询所有的商品列表
     *  @param pageNum
     *  @param pageSize
     * @return
     */
    Page<Product> findProducts(int pageNum, int pageSize);

    /**
     *  添加商品
     * @param product
     * @return
     */
    boolean addProduct(Product product);

    /**
     *  更新商品数据
     * @param product
     * @return
     */
    boolean updateProduct(Integer id,Product product);

    /**
     *  删除商品  逻辑删除
     * @param id
     * @return
     */
    Result deleteProductById(Integer id);

    Product findProduct(Integer id);

    Page<Product> searchProduct(String key, int page, int size);

    // 查询用户收藏的商品列表
    Page<Product> viewCollectedProduct(Integer buyerId, int page, int size);
}
