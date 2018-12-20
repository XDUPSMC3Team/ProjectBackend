package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.ProductVO;
import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.ProductCollectDao;
import cn.xuyangl.onlineshopping.dao.ProductDao;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.AttributeKey;
import cn.xuyangl.onlineshopping.entity.AttributeValue;
import cn.xuyangl.onlineshopping.entity.Product;
import cn.xuyangl.onlineshopping.entity.ProductCollect;
import cn.xuyangl.onlineshopping.service.AttributeKeyService;
import cn.xuyangl.onlineshopping.service.AttributeValueService;
import cn.xuyangl.onlineshopping.service.ProductService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
    @Autowired
    private ProductCollectDao productCollectDao;
    @Autowired
    private AttributeKeyService attributeKeyService;
    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ProductCollectDao productCollectDao) {
        this.productDao = productDao;
        this.productCollectDao = productCollectDao;
    }

    @Override
    public Page<Product> findProducts(int pageNum, int pageSize) {

        //对pageNum 进行 规范判断
        if (pageNum<0)
        {
            pageNum = 0;
        }
        // 构建pageRequest

        Pageable pageable = new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id"));
        return productDao.findAllByStatus(0, pageable);
    }

    /**
     *  根据 shopId 查询所有的products
     * @param shopId
     * @return
     */
    @Override
    public List<Product> findProductsByShopId(String shopId) {
        return productDao.findAllByShopIdAndStatus(Integer.parseInt(shopId), 0);
    }

    /**
     *  添加一个商品主类
     * @param product
     * @return
     */
    @Override
    public ResultEnum addProduct(Product product) {
        System.out.println(product.getPic());
        // 补充商品属性
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setStatus(0);   //表示正常
        // Todo
        String attributeList = product.getAttributeList();
        // 添加Payment
        StringBuilder stringBuilder = new StringBuilder(attributeList.substring(0,attributeList.length()-1));
        stringBuilder.append(",\"Payment\":[\"Wechat\",\"Alipay\"]}");
        product.setAttributeList(stringBuilder.toString());
        //{"memory":["4G", "8G"], "color":["red","black", "white"]}
        JSONObject jsonObject = new JSONObject(attributeList);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext())
        {
            String key = keys.next();
            AttributeKey byName = attributeKeyService.findByName(key);
            if (byName==null)
            {
                // 根据名称查找 attributeKey
                return ResultEnum.AttributeKeyNotFound;
            }
            String value = jsonObject.get(key).toString();
            value = value.substring(1,value.length()-1);
            String[] values = value.split(",");
            for (String s:values)
            {
                // 根据id 添加 value值
                AttributeValue attributeValue = new AttributeValue();
                attributeValue.setAttributeKeyId(byName.getId());
                attributeValue.setAttributeValue(s);
                attributeValueService.addAttributeValue(attributeValue);
            }
        }
        // 添加payment
        AttributeKey payment = attributeKeyService.findByName("Payment");
        String[] strings = new String[]{"Wechat","Alipay"};
        if (payment!=null)
        {
            for (String s:strings)
            {
                AttributeValue attributeValue = new AttributeValue();
                attributeValue.setAttributeKeyId(payment.getId());
                attributeValue.setAttributeValue(s);
                attributeValueService.addAttributeValue(attributeValue);
            }
        }
        productDao.save(product);
        return ResultEnum.Success;
    }

    /**
     *  更新商品
     * @param id
     * @param product
     * @return
     */
    @Override
    public ResultEnum updateProduct(Integer id,Product product) {
        // 判断该商品是否存在
        if (productDao.findOne(id)==null)
        {
            return ResultEnum.ProductNotFound;
        }
        String attributeList = product.getAttributeList();
        //{"memory":["4G", "8G"], "color":["red","black", "white"]}
        JSONObject jsonObject = new JSONObject(attributeList);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext())
        {
            String key = keys.next();
            AttributeKey byName = attributeKeyService.findByName(key);
            if (byName==null)
            {
                // 根据名称查找 attributeKey
                return ResultEnum.AttributeKeyNotFound;
            }
            String value = jsonObject.get(key).toString();
            value = value.substring(1,value.length()-1);
            String[] values = value.split(",");
            // 根据 id 删除 attributeValue
            attributeValueService.delelteByAttributeKey(byName.getId());
            for (String s:values)
            {
                // 根据id 添加 value值
                AttributeValue attributeValue = new AttributeValue();
                attributeValue.setAttributeKeyId(byName.getId());
                attributeValue.setAttributeValue(s);
                attributeValueService.addAttributeValue(attributeValue);
            }
        }
        productDao.saveAndFlush(product);
        return ResultEnum.Success;
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
        if (page<0) page = 0;
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"));
        return productDao.findAllByNameContainingAndStatus(key,0, pageable);
    }

    @Override
    public Page<Product> viewCollectedProduct(Integer buyerId, int page, int size) {
        List<ProductCollect> pcs = productCollectDao.findAllByBuyerId(buyerId);
        List<Integer> prodIds = new ArrayList<>();
        for (ProductCollect pc : pcs) {
            prodIds.add(pc.getProductId());
        }
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"));
        return productDao.findAllByIdIn(prodIds, pageable);
    }

    @Override
    public ProductVO findProductAsBuyer(Integer buyerId, Integer productId) {
        Product product = productDao.findOne(productId);
        ProductCollect pc = productCollectDao.findByBuyerIdAndProductId(buyerId, productId);
        Integer collectId = 0;
        if (pc != null) {
            collectId = pc.getId();
        }
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        productVO.setCollectId(collectId);
        return productVO;
    }

    @Override
    public Page<Product> findProductsByCategoryId(Integer categoryId, int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"));
        return productDao.findAllByCategoryIdAndStatus(categoryId, 0, pageable);
    }
}
