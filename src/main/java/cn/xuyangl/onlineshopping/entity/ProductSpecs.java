package cn.xuyangl.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import groovy.lang.GrabExclude;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description 商品详情实体
 *
 * id
product_id
detail 详细属性
stock 库存
price 单价 decimal(8,2)
create_time
update_time
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/28 21:23
 */
@Entity
@Table(name = "T_PRODUCT_SPECS")
@Data
public class ProductSpecs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer productId;

    //例如: `{"memory": "4G", "color": "red"}`
    private String detail;  //详细属性

    private Integer stock;  //库存

    private Double price;   //单价

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;   //创建时间

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;   //更新时间

}
