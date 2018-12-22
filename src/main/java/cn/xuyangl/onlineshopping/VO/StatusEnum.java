package cn.xuyangl.onlineshopping.VO;

import lombok.Data;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/22 15:18
 *
 * status 订单状态（0已下单/1已发货/2运输中/3已收货/4已评价/5退货中/6退货成功）
 */
public enum StatusEnum {

    OrderPlaced("已下单",0),Shipped("已发货",1),InTransit("运输中",2)
    ,Received("已收货",3),Reviewed("已评价",4);
    public String  name;
    public Integer code;
    StatusEnum(String name,Integer code)
    {
        this.name = name;
        this.code = code;
    }

}
