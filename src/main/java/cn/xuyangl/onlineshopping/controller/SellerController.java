package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.entity.*;
import cn.xuyangl.onlineshopping.model.LoginForm;
import cn.xuyangl.onlineshopping.service.*;
import cn.xuyangl.onlineshopping.utils.JwtToken;
import cn.xuyangl.onlineshopping.utils.MailUtil;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private  ShopService shopService;

    /**
     *  seller 注册
     * @param seller
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Seller seller) {
        return sellerService.register(seller);
    }

    /**
     *  seller 登录
     * @param loginForm
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpSession session)
    {
        Seller byEmail = sellerService.findByEmail(loginForm.getUsername());
        if (byEmail==null)
        {
            return ResultUtil.error(ResultEnum.AccountNotFound);
        }
        // 判断用户名 密码  以及状态
        if (byEmail.getPassword()!=null&&byEmail.getPassword().equals(loginForm.getPassword())&&byEmail.getStatus()==0)
        {
            // 判断密码是否相等
            //将数据存入cookie中i
            session.setAttribute(Common.USER_TYPE, "seller");
            session.setAttribute(Common.BUYER_ID, byEmail.getId());
            session.setAttribute(Common.USERNAME, byEmail.getUsername());
            return ResultUtil.success(byEmail.getId());
        }else{
            return ResultUtil.error(ResultEnum.PasswordIncorrect);
        }
    }

    @RequestMapping(value = "/password",method = RequestMethod.GET)
    public Result forgetPassword(@RequestParam("email") String email)
    {
        // 根据邮箱查询用户
        Seller byEmail = sellerService.findByEmail(email);
        if (byEmail==null)
        {
            return ResultUtil.error(ResultEnum.AccountNotFound);
        }else{
            SimpleMailMessage simpleMailMessage = MailUtil.createMessage(email, "主题：密码找回", "您的密码为："+byEmail.getPassword());
            javaMailSender.send(simpleMailMessage);
            return ResultUtil.success("send success");
        }
    }

    /**
     *  商家注册
     * @param shop
     * @return
     */
    @PostMapping
    @RequestMapping("/register/shop")
    public Result registerShop(@RequestBody Shop shop)
    {
        // 根据 email 查找 buyer 判断是否存在
//        Buyer buyer = buyerService.findByEmail(email);
//        Seller seller = sellerService.findByEmail(email);
//        if (seller==null)
//        {
//            Seller seller1 = new Seller();
//            seller.setRealName(buyer.getRealName());
//            seller.setEmail(buyer.getEmail());
//            seller.setAddress(buyer.getAddress());
//            seller.setPassword(buyer.getPassword());
//            seller.setPhone(buyer.getPhone());
//            sellerService.register(seller1);
//        }
        // 首先判断是否登录
//        if (token==null||"".equals(token))
//        {
//            return ResultUtil.error(ResultEnum.NO_RIGHT);
//        }

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
        ResultEnum resultEnum = productService.addProduct(product);
        if (resultEnum.code==0)
        {
            return ResultUtil.success();
        }else{
            return ResultUtil.error(resultEnum);
        }
    }

    /**
     * 根据 shopId 查询 product
     * @param shopId
     * @return
     */
    @PostMapping
    @RequestMapping("/products/{shopId}")
    public Result addProduct(@PathVariable("shopId") String  shopId)
    {
        return ResultUtil.success(productService.findProductsByShopId(shopId));
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
    public Result addAttributeKey(@RequestBody AttributeKey attributekey)
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
     * 增加多组 attributeValues
     */
    @RequestMapping(value = "/attributeValues",method = RequestMethod.POST)
    public Result addAttributeValues(@RequestBody List<AttributeValue> attributeValues)
    {
        attributeValues.forEach(attributeValue -> attributeValueService.addAttributeValue(attributeValue));
        return ResultUtil.success();
    }

    /**
     *  根据 attributekey 查找  attributeValue
     * @param attributeKey
     * @return
     */
    @RequestMapping(value = "/attributeValue/{attributeKey}",method = RequestMethod.GET)
    public Result findAttributeValueById(@PathVariable("attributeKey")String attributeKey)
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

    /**
     *  根据sellerId  查询 shop 商铺信息
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/shop/{sellerId}",method = RequestMethod.GET)
    public Result findShopById(@PathVariable("sellerId")String sellerId)
    {
        return ResultUtil.success(shopService.findShopById(sellerId));
    }
}
