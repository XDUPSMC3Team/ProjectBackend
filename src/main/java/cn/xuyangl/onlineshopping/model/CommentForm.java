package cn.xuyangl.onlineshopping.model;

import lombok.Data;

/**
 * @author xjin
 * created on 2019/1/3 10:40 PM
 */
@Data
public class CommentForm {
    private Integer orderDetailId;
    private String content;
}
