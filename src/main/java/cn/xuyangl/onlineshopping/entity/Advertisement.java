package cn.xuyangl.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/3 11:40
 */
@Data
@Entity
@Table(name = "T_ADVERTISEMENT")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String link;    //广告链接地址

    private String pic; // 广告图片

    private String content; //广告图片

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;    //广告开始时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;    //广告开始时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;    //广告开始时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updateTime;    //广告开始时间

    @Column(columnDefinition = "int default 0")
    private Integer deleted; // 是否被删除默认是0 表示正常

}
