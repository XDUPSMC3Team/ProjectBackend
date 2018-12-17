package cn.xuyangl.onlineshopping.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderVO {
    private Integer id;
    private Integer buyerId;
    private String receiverName;    //收货人姓名
    private Double money;   //金钱
    private Integer status; //订单状态
    private Integer payStatus;  //支付状态
    private Integer productId;
    private String productName;
    private Integer amount;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;  //收货时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;   //创建时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;   //更新时间
}
