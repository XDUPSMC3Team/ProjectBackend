package cn.xuyangl.onlineshopping.VO;

import lombok.Data;

import java.util.Date;

/**
 * @author xjin
 * created on 2019/1/4 12:10 PM
 */
@Data
public class CommentVO {
    private String username;
    private String commentContent;
    private Date createTime;
}
