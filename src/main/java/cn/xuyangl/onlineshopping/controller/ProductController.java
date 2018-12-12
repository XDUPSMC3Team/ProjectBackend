package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.service.ProductService;
import cn.xuyangl.onlineshopping.service.ProductSpecsService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @Autowired
    private ProductSpecsService productSpecsService;

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

    /**
     * 查询单个商品具体信息
     */
    @GetMapping("/{productId}")
    public Result findProduct(@PathVariable("productId") Integer id, HttpSession session) {
        String userType = (String) session.getAttribute(Common.USER_TYPE);
        if (userType!=null&&userType.equals("buyer")) {
            Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
            return ResultUtil.success(productService.findProductAsBuyer(buyerId, id));
        }
        return ResultUtil.success(productService.findProduct(id));
    }

    /**
     * 搜索商品
     */
    @GetMapping("/search")
    public Result searchProduct(@RequestParam(name = "key", required = false, defaultValue = "") String key,
                                @RequestParam(name = "pageNo", required = false, defaultValue = "0") String pageNo,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") String pageSzie)
    {
        return ResultUtil.success(productService.searchProduct(key, Integer.parseInt(pageNo), Integer.parseInt(pageSzie)));
    }

    /**
     *  根据productId 查询所有 productSpecs
     * @param productId
     * @return
     */
    @GetMapping("/productSpecs/{productId}")
    public Result findAllProductSpecs(@PathVariable String productId) {
        return ResultUtil.success(productSpecsService.findAllByProductId(productId));
    }

    @GetMapping("/productSpecs")
    public Result findAllProductSpecs(@RequestParam(name = "pageNo",required = false,defaultValue = "0") String pageNo
            , @RequestParam(name="pageSize",required = false,defaultValue = "10")String  pageSize)
    {
        return ResultUtil.success(productSpecsService.findAll(Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }

    @PostMapping("/productSpecs/detail")
    public Result findProductSpecs(@RequestBody String detail)
    {
        return ResultUtil.success(productSpecsService.findProductsSpecs(detail));
    }

}
