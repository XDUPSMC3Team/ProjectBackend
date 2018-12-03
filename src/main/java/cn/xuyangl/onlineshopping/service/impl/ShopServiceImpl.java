package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.model.ShopDetail;
import cn.xuyangl.onlineshopping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/3 11:18
 */
@Service
public class ShopServiceImpl implements ShopService {


    @Autowired
    private ShopDao shopDao;

    @Override
    public boolean modifyShopDetail(Integer shopId,ShopDetail shopDetail) {

        Shop one = shopDao.findOne(shopId);
        if (one==null)
        {
            return false;
        }
        if (shopDetail.getEmail()!=null&&!"".equals(shopDetail.getEmail()))
        {
            one.setEmail(shopDetail.getEmail());
        }
        if (shopDetail.getTelephone()!=null&&!"".equals(shopDetail.getTelephone()))
        {
            one.setPhone(shopDetail.getTelephone());
        }
        return true;
    }
}
