package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xjin
 * created on 2019/1/3 10:43 PM
 */
public interface CommentDao extends JpaRepository<Comment, Integer> {
    Comment findByBuyerIdAndOrderDetailId(Integer buyerId, Integer orderDetailId);

    Comment findByOrderDetailId(Integer orderDetailId);

    List<Comment> findAllByProductId(Integer productId);
}
