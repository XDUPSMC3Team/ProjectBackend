package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.service.ProductService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 16:16
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     *  分页查询所有的product
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/products")
    @GetMapping
    public Result findAllProducts(@RequestParam(name = "pageNo",required = false,defaultValue = "0") String pageNo
            , @RequestParam(name="pageSize",required = false,defaultValue = "10")String  pageSize)
    {
        return ResultUtil.success(productService.findProducts(Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }
}
