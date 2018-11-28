package cn.xuyangl.onlineshopping.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description 类别
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/28 21:15
 */
@Entity
@Table(name = "T_CATEGORY")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer parentId; //父级目录

    private String name;
}
