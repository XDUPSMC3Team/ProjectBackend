package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.VO.ShopVO;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.model.ShopDetailForm;
import cn.xuyangl.onlineshopping.model.WithdrawData;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/3 11:16
 */

public interface ShopService {

    boolean modifyShopDetail(Integer shopId, ShopDetailForm shopDetal);

    // 查看用户收藏的商铺列表
    List<ShopVO> viewCollectedShop(Integer buyerId);

    // 查询所有的商铺信息
    Page<Shop> findAllShops(int pageNum, int pageSize);

    Shop findShopById(String sellerId);

    Shop findByShopId(Integer shopId);

    // 买家浏览店铺
    ShopVO buyerViewShop(Integer shopId, Integer buyerId);


    /**
     *  为shop 打广告
     * @param shopId
     * @param money
     * @return
     */
    ResultEnum advertise(Integer shopId,double money);

    /**
     *  提款
     * @param withdrawData
     * @return
     */
    Result withdraw(WithdrawData withdrawData);

}