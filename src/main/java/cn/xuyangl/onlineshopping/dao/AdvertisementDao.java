package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/5 14:52
 */
@Repository
public interface AdvertisementDao extends JpaRepository<Advertisement,Integer> {
}
