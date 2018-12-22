package cn.xuyangl.onlineshopping.VO;

import cn.xuyangl.onlineshopping.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjin
 * created on 2018/12/22 3:50 PM
 */
@Data
public class ShopVO {
    private Integer id;
    private Integer sellerId;
    private String shopName;
    private String shopDesc;
    private String phone;   // 商家电话
    private String email;      // 商家邮箱
    private Integer status;

    private Integer collectId;
    List<Product> products = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
