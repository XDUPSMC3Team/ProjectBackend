package cn.xuyangl.onlineshopping.VO;

import lombok.Data;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/22 15:18
 *
 * status 订单状态（0已下单/1已发货/2运输中/3已收货/4已评价）
 */
public enum StatusEnum {

    Cancelled("cancelled",-1),OrderPlaced("order placed",0),Shipped("shipped",1),InTransit("in transit",2)
    ,Received("received",3),Reviewed("reviewed",4);
    public String  name;
    public Integer code;
    StatusEnum(String name,Integer code)
    {
        this.name = name;
        this.code = code;
    }

}
