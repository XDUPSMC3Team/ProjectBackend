package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.SellerDao;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.Seller;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.service.SellerService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 10:43
 */
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private ShopDao shopDao;

    /**
     *  seller 注册
     * @param seller
     * @return
     */
    @Override
    public Boolean register(Seller seller) {

        // 补充默认属性的值
        seller.setStatus(0);    // 表示为正常用户
        seller.setCreateTime(LocalDateTime.now());
        try{
            sellerDao.save(seller);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public Seller findByEmail(String email) {
        Seller sellerByEmail = sellerDao.findSellerByEmail(email);
        return sellerByEmail;
    }

    @Override
    public boolean registerShop(Shop shop) {
        // 补全属性
        shop.setStatus(0);  // 设置为受理中
        shop.setCreateTime(LocalDateTime.now());
        try{
            shopDao.save(shop);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
