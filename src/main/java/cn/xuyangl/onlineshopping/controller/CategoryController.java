package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.entity.Category;
import cn.xuyangl.onlineshopping.service.CategoryService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:10
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     *  查找所有的category
     * @return
     */
    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public Result findAllCategory()
    {
        return ResultUtil.success(categoryService.findAllCategory());
    }

    /**
     *  添加 category
     * @param category
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result addCategory(@RequestBody Category category)
    {
        categoryService.addCategory(category);
        return ResultUtil.success();
    }


}
