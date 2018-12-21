package cn.xuyangl.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description 订单主表
 *
 *
 * id

buyer_id

receiver_name 收货人姓名

receiver_address 收货地址B

money 总金额 decimal(8,2)

status 订单状态（0已下单/1已发货/2运输中/3已收货/4已评价/5退货中/6退货成功）

pay_status 支付状态 （0未支付/1已支付）

receive_time 收货时间

create_time （在这里可作为下单时间使用）

update_time
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/28 21:29
 */

@Entity
@Data
@Table(name = "T_ORDER_MASTER")
public class OrderMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer shopId; //店家id
    private Integer buyerId;
    private String receiverName;    //收货人姓名
    private String address;
    private Double money;   //金钱
    private Integer status = 0; //订单状态
    private Integer payStatus = 0;  //支付状态
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;  //收货时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;   //创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;   //更新时间
}


