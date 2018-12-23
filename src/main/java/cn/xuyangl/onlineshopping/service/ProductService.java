package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.CollectProductVO;
import cn.xuyangl.onlineshopping.VO.ProductVO;
import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
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

    List<Product> findProductsByShopId(String shopId);

    /**
     *  添加商品
     * @param product
     * @return
     */
    ResultEnum addProduct(Product product);

    /**
     *  更新商品数据
     * @param product
     * @return
     */
    ResultEnum updateProduct(Integer id,Product product);

    /**
     *  删除商品  逻辑删除
     * @param id
     * @return
     */
    Result deleteProductById(Integer id);

    Product findProduct(Integer id);

    Page<Product> searchProduct(String key, int page, int size);

    // 查询用户收藏的商品列表
    List<ProductVO> viewCollectedProduct(Integer buyerId);

    // 作为买家查看商品，有收藏字段
    ProductVO findProductAsBuyer(Integer buyerId, Integer productId);
}

// 按照分类查看商品列表
Page<Product> findProductsByCategoryId(Integer categoryId, int page, int size);
