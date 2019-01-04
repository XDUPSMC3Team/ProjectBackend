package cn.xuyangl.onlineshopping.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjin
 * created on 2018/12/11 9:04 PM
 */
@Data
public class ProductVO {
    private Integer id;

    private Integer shopId; // 商铺id

    private String shopName;

    private String shopDesc;

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

    private Integer collectId; // 收藏唯一标识，为0表示当前用户未收藏该商品

    List<CommentVO> commentList = new ArrayList<>();
}
