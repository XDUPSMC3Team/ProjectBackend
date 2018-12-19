package cn.xuyangl.onlineshopping.model;

import lombok.Data;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/19 14:52
 */
@Data
public class OrderDetailData {

    private Integer productId;
    private String productName;
    private Integer amount;
    private Double price;
}
