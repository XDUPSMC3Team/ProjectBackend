package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.VO.ShopVO;
import cn.xuyangl.onlineshopping.dao.ProductDao;
import cn.xuyangl.onlineshopping.dao.ShopCollectDao;
import cn.xuyangl.onlineshopping.dao.ShopDao;
import cn.xuyangl.onlineshopping.dao.WithdrawalRecordDao;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.model.ShopDetailForm;
import cn.xuyangl.onlineshopping.model.WithdrawData;
import cn.xuyangl.onlineshopping.service.ShopService;
import cn.xuyangl.onlineshopping.service.WithdrawalRecordService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import com.sun.scenario.effect.impl.prism.PrDrawable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private WithdrawalRecordDao withdrawalRecordDao;


    @Autowired
    public ShopServiceImpl(ShopDao shopDao,
                           ShopCollectDao shopCollectDao,
                           ProductDao productDao,WithdrawalRecordDao withdrawalRecordDao) {
        this.shopDao = shopDao;
        this.shopCollectDao = shopCollectDao;
        this.productDao = productDao;
        this.withdrawalRecordDao = withdrawalRecordDao;
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
    public List<ShopVO> viewCollectedShop(Integer buyerId) {
        List<ShopCollect> scs = shopCollectDao.findAllByBuyerId(buyerId);
        List<Integer> shopIds = new ArrayList<>();
        for (ShopCollect sc : scs) {
            shopIds.add(sc.getShopId());
        }
        List<Shop> shops = shopDao.findAllByIdIn(shopIds);
        List<ShopVO> shopVOS = new ArrayList<>();
        for (Shop shop : shops) {
            ShopVO shopVO = new ShopVO();
            BeanUtils.copyProperties(shop, shopVO);
            shopVO.setCollectId(shopCollectDao.findByBuyerIdAndShopId(buyerId, shop.getId()).getId());
            shopVOS.add(shopVO);
        }
        return shopVOS;
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

    /**
     *  为商铺打广告
     * @param shopId
     * @param money
     * @return
     */
    @Override
    public ResultEnum advertise(Integer shopId, double money) {
        // 查询shop 是否存在
        if (shopDao.findOne(shopId)==null)
        {
            return ResultEnum.SHOP_NOT_FOUND;
        }
        shopDao.addAdmoney(shopId,money);
        return ResultEnum.Success;
    }


    /**
     *  取款
     * @param withdrawData
     * @return
     */
    @Override
    public Result withdraw(WithdrawData withdrawData) {
        /**
         *  首先查询到该shop
         */
        Shop one = shopDao.findOne(withdrawData.getShopId());
        /**
         * 判断提款金额是否大于账户余额
         */
        if (one.getAccount()<withdrawData.getMoney())
        {
            return ResultUtil.error(ResultEnum.MoneyNotEnough);
        }
        WithdrawalRecord withdrawalRecord = new WithdrawalRecord();
        withdrawalRecord.setAccount(withdrawData.getAccountId());
        withdrawalRecord.setCreateTime(LocalDateTime.now());
        withdrawalRecord.setMoney(withdrawData.getMoney());
        withdrawalRecord.setName(one.getShopName());
        withdrawalRecordDao.save(withdrawalRecord);
        return ResultUtil.success();
    }
}
