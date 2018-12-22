package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.ShopVO;
import cn.xuyangl.onlineshopping.dao.ProductDao;
import cn.xuyangl.onlineshopping.dao.ShopCollectDao;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.entity.Product;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.entity.ShopCollect;
import cn.xuyangl.onlineshopping.model.ShopDetailForm;
import cn.xuyangl.onlineshopping.service.ShopService;
import com.sun.scenario.effect.impl.prism.PrDrawable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/3 11:18
 */
@Service
public class ShopServiceImpl implements ShopService {


    private ShopDao shopDao;
    private ShopCollectDao shopCollectDao;
    private ProductDao productDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao,
                           ShopCollectDao shopCollectDao,
                           ProductDao productDao) {
        this.shopDao = shopDao;
        this.shopCollectDao = shopCollectDao;
        this.productDao = productDao;
    }

    @Override
    public boolean modifyShopDetail(Integer shopId,ShopDetailForm shopDetailForm) {

        Shop one = shopDao.findOne(shopId);
        if (one==null)
        {
            return false;
        }
        if (shopDetailForm.getEmail()!=null&&!"".equals(shopDetailForm.getEmail()))
        {
            one.setEmail(shopDetailForm.getEmail());
        }
        if (shopDetailForm.getTelephone()!=null&&!"".equals(shopDetailForm.getTelephone()))
        {
            one.setPhone(shopDetailForm.getTelephone());
        }
        shopDao.save(one);
        return true;
    }

    @Override
    public Page<Shop> viewCollectedShop(Integer buyerId, int page, int size) {
        List<ShopCollect> scs = shopCollectDao.findAllByBuyerId(buyerId);
        List<Integer> shopIds = new ArrayList<>();
        for (ShopCollect sc : scs) {
            shopIds.add(sc.getShopId());
        }
        Pageable pageable = new PageRequest(page, size);
        return shopDao.findAllByIdIn(shopIds, pageable);
    }


    /**
     *  查询所有 的shop
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Shop> findAllShops(int pageNum, int pageSize) {

        //对pageNum 进行 规范判断
        if (pageNum<1)
        {
            pageNum = 1;
        }
        // 构建pageRequest
        Pageable pageable = new PageRequest(pageNum-1,pageSize);
        return shopDao.findAll(pageable);
    }

    /**
     *  根据 sellerId 查询shop
     * @param sellerId
     * @return
     */
    @Override
    public Shop findShopById(String sellerId) {
        return shopDao.findShopBySellerId(Integer.parseInt(sellerId));
    }

    @Override
    public Shop findByShopId(Integer shopid) {
        return shopDao.findOne(shopid);
    }

    @Override
    public ShopVO buyerViewShop(Integer shopId, Integer buyerId) {
        Shop shop = shopDao.findOne(shopId);
        ShopCollect sc = shopCollectDao.findByBuyerIdAndShopId(buyerId, shopId);
        Integer collectId = 0;
        if (sc != null) {
            collectId = sc.getId();
        }
        ShopVO shopVO = new ShopVO();
        BeanUtils.copyProperties(shop, shopVO);
        shopVO.setCollectId(collectId);
        List<Product> products = productDao.findAllByShopIdAndStatus(shopId, 0);
        shopVO.setProducts(products);
        return shopVO;
    }
}
