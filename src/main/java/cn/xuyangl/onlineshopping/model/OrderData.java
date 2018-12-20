package cn.xuyangl.onlineshopping.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/19 14:51
 */
@Data
public class OrderData {
    private Integer id;
    private Integer buyerId;
    private String receiverName;    //收货人姓名
    private Double money;   //金钱
    private Integer status; //订单状态
    private Integer payStatus;  //支付状态
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;  //收货时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;   //创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;   //更新时间

    /**
     *  OrderMaster 和 OrderDetail 为一对多关系
     *  相当于一个订单会包含有多个商品
     */
    List<OrderDetailData> orderDetailDataList = new ArrayList<>();
}
