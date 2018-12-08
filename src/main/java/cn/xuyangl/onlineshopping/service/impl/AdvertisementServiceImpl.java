package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.AdvertisementDao;
import cn.xuyangl.onlineshopping.entity.Advertisement;
import cn.xuyangl.onlineshopping.service.AdvertisementService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/5 14:53
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService{

    @Autowired
    private AdvertisementDao advertisementDao;

    @Override
    public void addAd(Advertisement advertisement) {
        advertisementDao.save(advertisement);
    }

    @Override
    public Result updateAd(Integer id, Advertisement advertisement) {

        // 根据id 查找 ad
        Advertisement one = advertisementDao.findOne(id);
        if (one==null)
        {
            return ResultUtil.error(ResultEnum.AD_NOT_EXIST);
        }
        // 更新属性
        one.setName(advertisement.getName());
        one.setContent(advertisement.getContent());
        one.setStartTime(advertisement.getStartTime());
        one.setEndTime(advertisement.getEndTime());
        one.setUpdateTime(LocalDateTime.now());
        advertisementDao.saveAndFlush(one);
        return ResultUtil.success();
    }

    @Override
    public Result deleteAdById(Integer adId) {
        Advertisement one = advertisementDao.findOne(adId);
        if(one==null)
        {
            return ResultUtil.error(ResultEnum.AD_NOT_EXIST);
        }
        one.setDeleted(1);  //表示为删除
        advertisementDao.saveAndFlush(one);
        return ResultUtil.success();
    }
}
