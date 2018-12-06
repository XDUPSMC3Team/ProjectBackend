package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.Category;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:07
 */
public interface CategoryService {

    void addCategory(Category category);

    List<Category> findAllCategory();
}
