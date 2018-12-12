package cn.xuyangl.onlineshopping.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description 商品属性key表
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/30 09:29
 */
@Entity
@Table(name ="T_ATTR_KEY")
@Data
public class AttributeKey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer categoryId;
    private String  attributeKey;   //属性key名称，例如：内存，颜色，尺码
}
