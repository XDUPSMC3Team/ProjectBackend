package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 类别dao
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:05
 */
public interface CategoryDao extends JpaRepository<Category,Integer>{
}
