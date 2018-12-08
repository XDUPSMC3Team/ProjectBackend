package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.dao.AttributeKeyDao;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.service.*;
import cn.xuyangl.onlineshopping.utils.JwtToken;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import com.auth0.jwt.interfaces.Claim;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Attribute;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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

    @Autowired
    private ProductSpecsService productSpecsService;

    @Autowired
    private AttributeKeyService attributeKeyService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private BuyerService buyerService;

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
        // 判断用户名 密码  以及状态
        if (byEmail.getPassword()!=null&&byEmail.getPassword().equals(password)&&byEmail.getStatus()==0)
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
    public Result registerShop(@RequestBody Shop shop,@CookieValue(value = "token")String token)
    {
        String email = JwtToken.verifyToken(token).get("email").asString();
        // 根据 email 查找 buyer 判断是否存在
        Buyer buyer = buyerService.findByEmail(email);
        Seller seller = sellerService.findByEmail(email);
        if (seller==null)
        {
            Seller seller1 = new Seller();
            seller.setRealName(buyer.getRealName());
            seller.setEmail(buyer.getEmail());
            seller.setAddress(buyer.getAddress());
            seller.setPassword(buyer.getPassword());
            seller.setPhone(buyer.getPhone());
            sellerService.register(seller1);
        }
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
    public Result updateProduct(@PathVariable("productId")String productId,@RequestBody Product product)
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
    @RequestMapping("/product/delete/{productId}")
    public Result deleteProduct(@PathVariable("productId")String productId)
    {
        Result result = productService.deleteProductById(Integer.parseInt(productId));
        return result;
    }


    /**
     *  管理 广告
     */

    /**
     *  增加一条 attributeKey
     * @param attributekey
     * @return
     */
    @RequestMapping(value = "/attributeKey",method = RequestMethod.POST)
    public Result addAttributeKey(@RequestBody Attributekey attributekey)
    {
        attributeKeyService.addAttributeKey(attributekey);
        return ResultUtil.success();
    }
    /**
     *  根据categoryid 查找 attributeKey
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/attributeKey/{categoryId}",method = RequestMethod.GET)
    public Result findAttributeKeyById(@PathVariable("categoryId")String categoryId)
    {
        return ResultUtil.success(attributeKeyService.findByCategoryId(Integer.parseInt(categoryId)));
    }

    /**
     *  增加一条 attributeValue 记录
     * @param attributeValue
     * @return
     */
    @RequestMapping(value = "/attributeValue",method = RequestMethod.POST)
    public Result addAttributeValue(@RequestBody AttributeValue attributeValue)
    {
        attributeValueService.addAttributeValue(attributeValue);
        return ResultUtil.success();
    }

    /**
     *  根据 attributekey 查找  attributeValue
     * @param attributeKey
     * @return
     */
    @RequestMapping(value = "/attributeValue/{attributeKey}",method = RequestMethod.GET)
    public Result findAttributeValueById(@PathVariable("attributekey")String attributeKey)
    {
        return ResultUtil.success(attributeValueService.findByAttributeKey(Integer.parseInt(attributeKey)));
    }
    /**
     *  增加一份productSpecs
     * @param productSpecs
     * @return
     */
    @RequestMapping(value = "/productSpecs",method = RequestMethod.POST)
    public Result addProductSpecs(@RequestBody ProductSpecs productSpecs)
    {
        productSpecsService.addProductSpecs(productSpecs);
        return ResultUtil.success();
    }
}
