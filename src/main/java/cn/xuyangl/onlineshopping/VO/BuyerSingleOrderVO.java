package cn.xuyangl.onlineshopping.VO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xjin
 * created on 2018/12/21 3:35 PM
 */
@Data
public class BuyerSingleOrderVO {
    private Integer id;
    private Integer shopId;
    private Double money;
    private Integer status;
    private Integer payStatus;

    List<BuyerOrderDetailVO> orderDetailVOS = new ArrayList<>();
}
