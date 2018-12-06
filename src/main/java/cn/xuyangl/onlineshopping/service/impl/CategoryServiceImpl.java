package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.CategoryDao;
import cn.xuyangl.onlineshopping.entity.Category;
import cn.xuyangl.onlineshopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:09
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void addCategory(Category category) {
        if (category!=null)
        {
            categoryDao.save(category);
        }
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryDao.findAll();
    }
}
