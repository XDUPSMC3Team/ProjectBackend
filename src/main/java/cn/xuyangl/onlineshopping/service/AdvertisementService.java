package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.entity.Advertisement;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/5 14:53
 */
public interface AdvertisementService {

    void addAd(Advertisement advertisement);
    Result updateAd(Integer integer, Advertisement advertisement);
    Result deleteAdById(Integer adId);

}
