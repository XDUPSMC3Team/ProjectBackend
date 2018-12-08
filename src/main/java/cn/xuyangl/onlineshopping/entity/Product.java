package cn.xuyangl.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/28 21:19
 */
@Entity
@Table(name = "T_PRODUCT")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer shopId; // 商铺id

    private Integer categoryId;

    private String name;    //商品名称

    private String pic;     // 商品图片

    /**
     *  attribute_list 属性选项，例如：`{"memory":["4G", "8G"], "color":["red","black", "white"]}`
     */
    public String attributeList ;   //属性选项
    private String description;     //商品描述

    private Integer status; // 商品状态 0 表示正常 1表示下架

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;   //创建时间

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;   //更新时间
}
