package cn.xuyangl.onlineshopping.model;

import lombok.Data;

import java.util.List;

/**
 * @Description 历史销售记录
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/29 12:02
 */
@Data
public class SaleHistoryData {
    private List<OrderData> daily;
    private List<OrderData> weekly;
    private List<OrderData> monthly;
    private List<OrderData> yearly;
}
