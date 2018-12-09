package cn.xuyangl.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description 商家实体类
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 11:34
 */
@Entity
@Table(name = "T_SHOP")
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer sellerId;
    @Column(unique = true)  //唯一性约束
    private String shopName;
    private String shopDesc;
    private String phone;   // 商家电话
    private String email;      // 商家邮箱
    private Integer status; // 商铺状态 0 表示正在受理中 1 表示受理成功 2 表示受理失败 3表示被封
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updateTime;
}
