package cn.xuyangl.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description
 * 买家实体类
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/28 20:55
 */
@Entity
@Table(name = "T_BUYER")
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    private String username;    // 昵称

    private String password;    // 密码

    private String realName;    //名字

    @Column(unique = true)
    private String phone;   //电话

    private String email;   //邮箱

    private String address; //地址

    @Column(columnDefinition = "int default 0")
    // 0表示正常，1表示被拉进黑名单
    private Integer status = 0; //状态 是否被注销

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;   //创建时间

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updateTime;   //更新时间




}
