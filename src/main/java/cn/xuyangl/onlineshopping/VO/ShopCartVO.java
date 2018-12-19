package cn.xuyangl.onlineshopping.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xjin
 * created on 2018/12/19 3:21 PM
 */
@Data
public class ShopCartVO {
    private Integer id;
    private Integer productId;
    private Integer specsId;
    private String name;
    private String detail;
    private Double price;
    private Integer amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;   //创建时间

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;   //更新时间
}
