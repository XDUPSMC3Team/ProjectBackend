package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.VO.ShopCartVO;
import cn.xuyangl.onlineshopping.dao.ProductDao;
import cn.xuyangl.onlineshopping.dao.ProductSpecsDao;
import cn.xuyangl.onlineshopping.dao.ShopCartDao;
import cn.xuyangl.onlineshopping.entity.Product;
import cn.xuyangl.onlineshopping.entity.ProductSpecs;
import cn.xuyangl.onlineshopping.entity.ShopCart;
import cn.xuyangl.onlineshopping.model.ProductCartForm;
import cn.xuyangl.onlineshopping.service.ShopCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjin
 * created on 2018/12/19 2:46 PM
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    private ProductDao productDao;
    private ProductSpecsDao productSpecsDao;
    private ShopCartDao shopCartDao;

    public ShopCartServiceImpl(ProductSpecsDao productSpecsDao, ShopCartDao shopCartDao, ProductDao productDao) {
        this.productSpecsDao = productSpecsDao;
        this.shopCartDao = shopCartDao;
        this.productDao = productDao;
    }
    @Override
    public void addCart(ProductCartForm productCartForm, Integer buyerId) {
        ShopCart shopCart = new ShopCart();
        shopCart.setProductId(productSpecsDao.findById(productCartForm.getSpecsId()).getProductId());
        shopCart.setSpecsId(productCartForm.getSpecsId());
        shopCart.setAmount(productCartForm.getAmount());
        shopCart.setBuyerId(buyerId);
        shopCart.setCreateTime(LocalDateTime.now());
        shopCart.setUpdateTime(LocalDateTime.now());

        shopCartDao.saveAndFlush(shopCart);
    }

    @Override
    public void updateCart(Integer cartId, Integer amount) {
        ShopCart shopCart = shopCartDao.findOne(cartId);
        shopCart.setAmount(amount);
        shopCart.setUpdateTime(LocalDateTime.now());
        shopCartDao.saveAndFlush(shopCart);
    }

    @Override
    public List<ShopCartVO> viewCart(Integer buyerId) {
        List<ShopCartVO> shopCartVOList = new ArrayList<>();
        List<ShopCart> shopCarts = shopCartDao.findAllByBuyerId(buyerId);
        for(ShopCart sc : shopCarts) {
            Product product = productDao.findById(sc.getProductId());
            ProductSpecs ps = productSpecsDao.findById(sc.getSpecsId());
            String detail = ps.getDetail();
            Double price = ps.getPrice();
            ShopCartVO scv = new ShopCartVO();
            BeanUtils.copyProperties(sc, scv);
            scv.setName(product.getName());
            scv.setPrice(price);
            scv.setDetail(detail);
            scv.setPic(product.getPic());
            shopCartVOList.add(scv);
        }
        return shopCartVOList;
    }

    @Override
    public void deleteCart(Integer cartId) {
        shopCartDao.delete(cartId);
    }
}
