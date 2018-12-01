package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.entity.Product;
import cn.xuyangl.onlineshopping.entity.Seller;
import cn.xuyangl.onlineshopping.entity.Shop;
import cn.xuyangl.onlineshopping.service.ProductService;
import cn.xuyangl.onlineshopping.service.SellerService;
import cn.xuyangl.onlineshopping.utils.JwtToken;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import com.auth0.jwt.interfaces.Claim;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description seller 控制器
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/1 10:48
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    /**
     *  seller 注册
     * @param seller
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Seller seller) {
        Boolean register = sellerService.register(seller);
        if (register) {
            sellerService.register(seller);
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.RegisterError);
        }
    }

    /**
     *  seller 登录
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(String email, String password, HttpServletResponse response)
    {
        Seller byEmail = sellerService.findByEmail(email);
        if (byEmail==null)
        {
            return ResultUtil.error(ResultEnum.AccountNotFound);
        }
        if (byEmail.getPassword()!=null&&byEmail.getPassword().equals(password))
        {
            // 判断密码是否相等
            //将数据存入cookie中i
            String jwtToken = JwtToken.createToken(email, byEmail.getRealName(), "seller");
            Cookie cookie = new Cookie("token",jwtToken);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24); // 设置过期时间
            response.addCookie(cookie);
            return ResultUtil.success();
        }else{
            return ResultUtil.error(ResultEnum.PasswordIncorrect);
        }
    }

    /**
     *  商家注册
     * @param shop
     * @param token
     * @return
     */
    @PostMapping
    @RequestMapping("/register/shop")
    public Result registerShop(Shop shop,@CookieValue(value = "token")String token)
    {
        // 首先判断是否登录
        if (token==null||"".equals(token))
        {
            return ResultUtil.error(ResultEnum.NO_RIGHT);
        }
        boolean b = sellerService.registerShop(shop);
        if (b)
        {
            return ResultUtil.success();
        }else{
            return ResultUtil.error(ResultEnum.RegisterError);
        }
    }

    /**
     * 增加一个product
     * @param product
     * @return
     */
    @PostMapping
    @RequestMapping("/product")
    public Result addProduct(@RequestBody Product product)
    {
        boolean b = productService.addProduct(product);
        if (b)
        {
            return ResultUtil.success();
        }else{
            return ResultUtil.error(ResultEnum.OPERATION_FAILURE);
        }
    }

    /**
     *  更新商品
     * @param productId
     * @param product
     * @return
     */
    @PostMapping
    @RequestMapping("/product/{productId}")
    public Result updateProduct(@PathVariable("productId")String productId,Product product)
    {
        boolean b = productService.updateProduct(Integer.parseInt(productId), product);
        if (b){
            return ResultUtil.success();
        }else{
            return ResultUtil.error(ResultEnum.UPDATE_FAILED);
        }
    }

    /**
     *  根据ID 删除商品
     * @param productId
     * @return
     */
    @DeleteMapping
    @RequestMapping("/product/{productId}")
    public Result deleteProduct(@PathVariable("productId")String productId)
    {
        Result result = productService.deleteProductById(Integer.parseInt(productId));
        return result;
    }
}
