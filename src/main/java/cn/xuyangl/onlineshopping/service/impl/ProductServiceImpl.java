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
        if (pageNum<1)
        {
            pageNum = 1;
        }
        // 构建pageRequest
        Pageable pageable = new PageRequest(pageNum-1,pageSize);
        return productDao.findAllByStatus(pageable,0);
    }

    /**
     *  根据 shopId 查询所有的products
     * @param shopId
     * @return
     */
    @Override
    public List<Product> findProductsByShopId(String shopId) {
        return productDao.findAllByShopId(Integer.parseInt(shopId));
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
                return ResultEnum.AccountNotFound;
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
        productDao.save(product);
        return ResultEnum.Success;
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

    @Override
    public Page<Product> viewCollectedProduct(Integer buyerId, int page, int size) {
        List<ProductCollect> pcs = productCollectDao.findAllByBuyerId(buyerId);
        List<Integer> prodIds = new ArrayList<>();
        for (ProductCollect pc : pcs) {
            prodIds.add(pc.getProductId());
        }
        Pageable pageable = new PageRequest(page, size);
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
}
