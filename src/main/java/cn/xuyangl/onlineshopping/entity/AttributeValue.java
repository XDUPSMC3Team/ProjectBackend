package cn.xuyangl.onlineshopping.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description 商品属性value表
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/30 09:31
 */
@Entity
@Table(name = "T_ATTR_VALUE")
@Data
public class AttributeValue {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer attributeKeyId;    //商品key的id

    private String attributeValue;  //
}
