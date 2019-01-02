package cn.xuyangl.onlineshopping.VO;

import cn.xuyangl.onlineshopping.entity.Product;
import cn.xuyangl.onlineshopping.entity.Shop;
import lombok.Data;

import java.util.List;

@Data
public class BiddingInfoVO {
    private List<Shop> shops;
    private List<Product> products;
}
