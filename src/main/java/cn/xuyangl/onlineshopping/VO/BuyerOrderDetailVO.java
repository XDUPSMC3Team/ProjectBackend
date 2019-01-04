package cn.xuyangl.onlineshopping.VO;

import lombok.Data;

/**
 * @author xjin
 * created on 2018/12/20 10:52 PM
 */
@Data
public class BuyerOrderDetailVO {
    private Integer orderDetailId;
    private Integer productId;
    private String productName;
    private Double price;
    private Integer amount;

    private String commentContent = "";
}
