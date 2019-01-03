package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xjin
 * created on 2019/1/3 10:43 PM
 */
public interface CommentDao extends JpaRepository<Comment, Integer> {
}
