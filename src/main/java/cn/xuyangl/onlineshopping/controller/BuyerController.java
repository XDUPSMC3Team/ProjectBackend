package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.entity.Buyer;
import cn.xuyangl.onlineshopping.model.BuyerProfileForm;
import cn.xuyangl.onlineshopping.model.LoginForm;
import cn.xuyangl.onlineshopping.service.BuyerService;
import cn.xuyangl.onlineshopping.service.ProductService;
import cn.xuyangl.onlineshopping.service.ShopService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author xjin
 * created on 2018/11/30 3:36 PM
 */
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private BuyerService buyerService;
    private ProductService productService;
    private ShopService shopService;

    @Autowired
    public BuyerController(BuyerService buyerService, ProductService productService, ShopService shopService) {
        this.buyerService = buyerService;
        this.productService = productService;
        this.shopService = shopService;
    }

    @PostMapping("/register")
    public Result register(@RequestBody Buyer buyer) {
        if (buyer.getUsername() == null || buyer.getPassword() == null || buyer.getEmail() == null) {
            return ResultUtil.error(ResultEnum.RegisterEmptyError);
        }
        ResultEnum resultEnum = buyerService.register(buyer);
        if (resultEnum.code == 0) {
            return ResultUtil.success("Welcome, " + buyer.getUsername());
        } else {
            return ResultUtil.error(resultEnum);
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpSession session) {
        Buyer buyerInDB = buyerService.findByUsername(loginForm.getUsername());
        if (buyerInDB == null) return ResultUtil.error(ResultEnum.AccountNotFound);
        Buyer buyer = buyerService.login(loginForm.getUsername(), loginForm.getPassword());
        if (buyer == null) return ResultUtil.error(ResultEnum.PasswordIncorrect);
        session.setAttribute(Common.USER_TYPE, "buyer");
        session.setAttribute(Common.BUYER_ID, buyer.getId());
        session.setAttribute(Common.USERNAME, buyer.getUsername());
        session.setMaxInactiveInterval(3600);
        return ResultUtil.success(buyer.getId());
    }

    // 查看个人资料
    @GetMapping("/profile")
    public Result profileView(HttpSession session) {
        Buyer buyer = buyerService.findById((Integer) session.getAttribute(Common.BUYER_ID));
        buyer.setPassword(null);
        return ResultUtil.success(buyer);
    }

    // 修改个人资料
    @PostMapping("/profile")
    public Result profileEdit(@RequestBody BuyerProfileForm buyerProfileForm, HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        Buyer buyer = buyerService.findById(buyerId);
        BeanUtils.copyProperties(buyerProfileForm, buyer);
        buyerService.save(buyer);
        return ResultUtil.success();
    }

    // 查看已收藏的商品
    @GetMapping("/collectProd")
    public Result viewCollectedProd(@RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                    HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return ResultUtil.success(productService.viewCollectedProduct(buyerId, pageNo, pageSize));
    }

    // 查看已收藏的店铺
    @GetMapping("/collectShop")
    public Result viewCollectedShop(@RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                    HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return ResultUtil.success(shopService.viewCollectedShop(buyerId, pageNo, pageSize));
    }

    // 收藏商品
    @GetMapping("/collectProd/{productId}")
    public Result collectProd(@PathVariable("productId") Integer productId, HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        Integer collectId = buyerService.collectProduct(buyerId, productId);
        return ResultUtil.success(collectId);
    }

    // 收藏店铺
    @GetMapping("/collectShop/{shopId}")
    public Result collectShop(@PathVariable("shopId") Integer shopId, HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        Integer collectId = buyerService.collectShop(buyerId, shopId);
        return ResultUtil.success(collectId);
    }

    // 取消收藏商品 type = 1
    @DeleteMapping("/collectProd/{collectId}")
    public Result cancelCollectProduct(@PathVariable("collectId") Integer collectId, HttpSession session) {
        buyerService.cancelCollect(collectId, 1);
        return ResultUtil.success();
    }
    // 取消收藏店铺 type = 2
    @DeleteMapping("/collectShop/{collectId}")
    public Result cancelCollectShop(@PathVariable("collectId") Integer collectId, HttpSession session) {
        buyerService.cancelCollect(collectId, 2);
        return ResultUtil.success();
    }
}
