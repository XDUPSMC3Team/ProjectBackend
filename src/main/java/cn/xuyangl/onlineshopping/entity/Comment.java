package cn.xuyangl.onlineshopping.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xjin
 * created on 2019/1/3 10:41 PM
 */
@Entity
@Table(name = "T_COMMENT")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    private Integer buyerId;
    private Integer productId;
    private Integer orderMasterId;
    private Integer orderDetailId;
    private String content;
    private Date createTime;
    private Date updateTime;
}
