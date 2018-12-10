package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.entity.Buyer;
import cn.xuyangl.onlineshopping.model.BuyerProfileForm;
import cn.xuyangl.onlineshopping.model.LoginForm;
import cn.xuyangl.onlineshopping.service.BuyerService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xjin
 * created on 2018/11/30 3:36 PM
 */
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private BuyerService buyerService;

    @Autowired
    BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/register")
    public Result register(@RequestBody Buyer buyer) {
        if (buyerService.register(buyer)) {
            return ResultUtil.success("Welcome, " + buyer.getUsername());
        } else {
            return ResultUtil.error(ResultEnum.RegisterError);
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
        return ResultUtil.success();
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
    public Result viewCollectedProd(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return null;
    }

    // 查看已收藏的店铺
    @GetMapping("/collectShop")
    public Result viewCollectedShop(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return null;
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
