package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.ProductDao;
import cn.xuyangl.onlineshopping.entity.Product;
import cn.xuyangl.onlineshopping.service.ProductService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 15:39
 */
@Service
public class ProductServiceImpl implements ProductService{



    @Autowired
    private ProductDao productDao;

    @Override
    public Page<Product> findProducts(int pageNum, int pageSize) {

        //对pageNum 进行 规范判断
        if (pageNum<1)
        {
            pageNum = 1;
        }
        // 构建pageRequest
        Pageable pageable = new PageRequest(pageNum-1,pageSize);
        return productDao.findAll(pageable);
    }

    @Override
    public boolean addProduct(Product product) {

        // 补充商品属性
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setStatus(0);   //表示正常
        try{
            productDao.save(product);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Integer id,Product product) {
        // 判断该商品是否存在
        if (productDao.findOne(id)==null)
        {
            return false;
        }
        productDao.saveAndFlush(product);
        return true;
    }

    public Result deleteProductById(Integer id)
    {
        // 查找该商品是否存在
        Product one = productDao.findOne(id);
        if (one==null)
        {
            return ResultUtil.error(ResultEnum.SHOP_NOT_FOUND);
        }
        // 设置status 为删除状态
        one.setStatus(1);
        // 更新数据库
        productDao.saveAndFlush(one);
        return ResultUtil.success();
    }

    @Override
    public Product findProduct(Integer id) {
        return productDao.findOne(id);
    }

    @Override
    public Page<Product> searchProduct(String key, int page, int size) {
        if (page<1) page = 1;
        Pageable pageable = new PageRequest(page-1, size);
        return productDao.findAllByNameContaining(key, pageable);
    }
}
