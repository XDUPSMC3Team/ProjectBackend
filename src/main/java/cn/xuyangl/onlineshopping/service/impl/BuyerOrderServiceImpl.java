package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.ProductDao;
import cn.xuyangl.onlineshopping.dao.ProductSpecsDao;
import cn.xuyangl.onlineshopping.entity.ProductSpecs;
import cn.xuyangl.onlineshopping.model.SimpleOrderForm;
import cn.xuyangl.onlineshopping.service.BuyerOrderService;
import org.springframework.stereotype.Service;

/**
 * @author xjin
 * created on 2018/12/20 9:38 AM
 */
@Service
public class BuyerOrderServiceImpl implements BuyerOrderService {

    private ProductDao productDao;
    private ProductSpecsDao productSpecsDao;

    public BuyerOrderServiceImpl(ProductDao productDao, ProductSpecsDao productSpecsDao) {
        this.productDao = productDao;
        this.productSpecsDao = productSpecsDao;
    }

    @Override
    public void simpleOrder(SimpleOrderForm sof, Integer buyerId) {
        // 减库存
        ProductSpecs ps = productSpecsDao.findById(sof.getSpecsId());
        ps.setStock(ps.getStock() - sof.getAmount());
        productSpecsDao.saveAndFlush(ps);

        // 生成订单
        Integer productId = ps.getProductId();
        Double price = ps.getPrice();
        String detail = ps.getDetail();
        Double money = price * sof.getAmount();
    }

    @Override
    public void cartOrder() {

    }
}
