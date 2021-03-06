package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.CommentVO;
import cn.xuyangl.onlineshopping.VO.ProductVO;
import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.*;
import cn.xuyangl.onlineshopping.entity.*;
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

import java.awt.*;
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

    private ProductDao productDao;
    private ProductCollectDao productCollectDao;
    private AttributeKeyService attributeKeyService;
    private AttributeValueService attributeValueService;
    private ShopDao shopDao;
    private CommentDao commentDao;
    private BuyerDao buyerDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao,
                              ProductCollectDao productCollectDao,
                              AttributeKeyService attributeKeyService,
                              AttributeValueService attributeValueService,
                              ShopDao shopDao,
                              CommentDao commentDao,
                              BuyerDao buyerDao) {
        this.productDao = productDao;
        this.productCollectDao = productCollectDao;
        this.attributeKeyService = attributeKeyService;
        this.attributeValueService = attributeValueService;
        this.shopDao = shopDao;
        this.commentDao = commentDao;
        this.buyerDao = buyerDao;
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
        // 补充商品属性
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setStatus(0);   //表示正常
        // Todo
        String attributeList = product.getAttributeList();
        // 添加Payment
        StringBuilder stringBuilder = new StringBuilder(attributeList.substring(0,attributeList.length()>=1?attributeList.length()-1:0));
        if (attributeList==null||"{}".equals(attributeList))
        {
            stringBuilder.append("\"Payment\":[\"Wechat\",\"Alipay\"]}");
        }else{
            stringBuilder.append(",\"Payment\":[\"Wechat\",\"Alipay\"]}");
            //{"memory":["4G", "8G"], "color":["red","black", "white"]}
            JSONObject jsonObject = new JSONObject(attributeList);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext())
            {
                String key = keys.next();
                AttributeKey byName = attributeKeyService.findByNameAndCategoryId(key,product.getCategoryId());
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
                    // 判断当前数据是否已经在数据库中
                    AttributeValue byAttributeKeyAndValue = attributeValueService.findByAttributeKeyAndValue(byName.getId(), s);
                    if (byAttributeKeyAndValue==null)
                    {
                        // 根据id 添加 value值
                        AttributeValue attributeValue = new AttributeValue();
                        attributeValue.setAttributeKeyId(byName.getId());
                        attributeValue.setAttributeValue(s);
                        attributeValueService.addAttributeValue(attributeValue);
                    }

                }
            }
        }
        product.setAttributeList(stringBuilder.toString());
        // 添加payment
        AttributeKey payment = attributeKeyService.findByNameAndCategoryId("Payment",product.getCategoryId());
        String[] strings = new String[]{"\"Wechat\"","\"Alipay\""};
        if (payment!=null)
        {
            for (String s:strings)
            {
                System.out.println(s);
                AttributeValue byAttributeKeyAndValue = attributeValueService.findByAttributeKeyAndValue(payment.getId(), s);
                if (byAttributeKeyAndValue==null)
                {
                    // 根据id 添加 value值
                    AttributeValue attributeValue = new AttributeValue();
                    attributeValue.setAttributeKeyId(payment.getId());
                    attributeValue.setAttributeValue(s);
                    attributeValueService.addAttributeValue(attributeValue);
                }
            }
        }
        System.out.println(product.getAttributeList());
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
            AttributeKey byName = attributeKeyService.findByNameAndCategoryId(key,product.getCategoryId());
            if (byName==null)
            {
                // 根据名称查找 attributeKey
                return ResultEnum.AttributeKeyNotFound;
            }
            String value = jsonObject.get(key).toString();
            value = value.substring(1,value.length()-1);
            String[] values = value.split(",");
            // 根据 id 删除 attributeValue
            attributeValueService.deleteByAttributeKey(byName.getId());
            for (String s:values)
            {
                if ("".equals(s))
                {
                    continue;
                }
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
    public ProductVO findProduct(Integer productId) {
        Product product = productDao.findOne(productId);
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        Shop shop = shopDao.findById(productVO.getShopId());
        productVO.setShopName(shop.getShopName());
        productVO.setShopDesc(shop.getShopDesc());
        // 该商品的评论列表
        List<Comment> comments = commentDao.findAllByProductId(productId);
        List<CommentVO> commentVOS = new ArrayList<>();
        for (Comment comment : comments) {
            String username = buyerDao.findById(comment.getBuyerId()).getUsername();
            String commentContent = comment.getContent();
            CommentVO commentVO = new CommentVO();
            commentVO.setUsername(username);
            commentVO.setCommentContent(commentContent);
            commentVOS.add(commentVO);
        }
        productVO.setCommentList(commentVOS);
        return productVO;
    }

    @Override
    public Page<Product> searchProduct(String key, int page, int size) {
        if (page<0) page = 0;
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"));
        return productDao.findAllByNameContainingAndStatus(key,0, pageable);
    }

    @Override
    public List<ProductVO> viewCollectedProduct(Integer buyerId) {
        List<ProductCollect> pcs = productCollectDao.findAllByBuyerId(buyerId);
        List<Integer> prodIds = new ArrayList<>();
        for (ProductCollect pc : pcs) {
            prodIds.add(pc.getProductId());
        }
        List<Product> products = productDao.findAllByIdIn(prodIds);
        List<ProductVO> productVOS = new ArrayList<>();
        for (Product product : products) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVO.setCollectId(productCollectDao.findByBuyerIdAndProductId(buyerId, product.getId()).getId());
            productVOS.add(productVO);
        }
        return productVOS;
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
        Shop shop = shopDao.findById(productVO.getShopId());
        productVO.setShopName(shop.getShopName());
        productVO.setShopDesc(shop.getShopDesc());
        productVO.setCollectId(collectId);
        // 该商品的评论列表
        List<Comment> comments = commentDao.findAllByProductId(productId);
        List<CommentVO> commentVOS = new ArrayList<>();
        for (Comment comment : comments) {
            String username = buyerDao.findById(comment.getBuyerId()).getUsername();
            String commentContent = comment.getContent();
            CommentVO commentVO = new CommentVO();
            commentVO.setUsername(username);
            commentVO.setCommentContent(commentContent);
            commentVOS.add(commentVO);
        }
        productVO.setCommentList(commentVOS);
        return productVO;
    }


    @Override
    public Page<Product> findProductsByCategoryId(Integer categoryId, int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"));
        return productDao.findAllByCategoryIdAndStatus(categoryId, 0, pageable);
    }

    @Override
    public ResultEnum addAdvertisementFee(Integer productId, double fee) {
        if (productDao.findOne(productId)==null)
        {
            return ResultEnum.ProductNotFound;
        }
        productDao.addAdFeeForProduct(productId,fee);
        return ResultEnum.Success;
    }
}
