package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.model.ShopDetailForm;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/3 11:16
 */
public interface ShopService {

    boolean modifyShopDetail(Integer shopId,ShopDetailForm shopDetal);
}
