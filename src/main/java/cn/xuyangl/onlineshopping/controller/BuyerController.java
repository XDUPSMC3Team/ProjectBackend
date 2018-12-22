package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.BuyerOrderVO;
import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.entity.Buyer;
import cn.xuyangl.onlineshopping.model.BuyerProfileForm;
import cn.xuyangl.onlineshopping.model.LoginForm;
import cn.xuyangl.onlineshopping.model.ProductCartForm;
import cn.xuyangl.onlineshopping.model.SimpleOrderForm;
import cn.xuyangl.onlineshopping.service.*;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    private ShopCartService shopCartService;
    private BuyerOrderService buyerOrderService;

    @Autowired
    public BuyerController(BuyerService buyerService,
                           ProductService productService,
                           ShopService shopService,
                           ShopCartService shopCartService,
                           BuyerOrderService buyerOrderService) {
        this.buyerService = buyerService;
        this.productService = productService;
        this.shopService = shopService;
        this.shopCartService = shopCartService;
        this.buyerOrderService = buyerOrderService;
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
    public Result viewCollectedProd(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return ResultUtil.success(productService.viewCollectedProduct(buyerId));
    }

    // 查看已收藏的店铺
    @GetMapping("/collectShop")
    public Result viewCollectedShop(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return ResultUtil.success(shopService.viewCollectedShop(buyerId));
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

    // 将商品加入购物车
    @PostMapping("/addCart")
    public Result addCart(@RequestBody ProductCartForm productCartForm, HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        shopCartService.addCart(productCartForm, buyerId);
        return ResultUtil.success();
    }

    // 修改购物车中某商品的数量
    @GetMapping("/updateCart/{cartId}")
    public Result updateCart(@PathVariable("cartId") Integer cartId, @RequestParam("amount") Integer amount) {
        if (amount < 1) return ResultUtil.error(1, "amount can't be less than 1");
        shopCartService.updateCart(cartId, amount);
        return ResultUtil.success();
    }

    // 浏览购物车
    @GetMapping("/viewCart")
    public Result viewCart(HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return ResultUtil.success(shopCartService.viewCart(buyerId));
    }

    // 从购物车中删除
    @DeleteMapping("/deleteCart/{cartId}")
    public Result deleteCart(@PathVariable("cartId") Integer cartId) {
        shopCartService.deleteCart(cartId);
        return ResultUtil.success();
    }

    // 直接下单
    @PostMapping("/order")
    public Result order(@RequestBody SimpleOrderForm simpleOrderForm, HttpSession session){
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        if (simpleOrderForm.getAmount() <= 0) return ResultUtil.error(ResultEnum.AmountLessThanOne);
        if (!buyerOrderService.checkNameAndAddress(buyerId)) return ResultUtil.error(ResultEnum.EmptyRealNameOrAddress);
        Integer orderId = buyerOrderService.simpleOrder(simpleOrderForm, buyerId);
        return ResultUtil.success(orderId);
    }

    // 从购物车下单
    @PostMapping("/orderByCart")
    public Result orderByCart() {
        // 从购物车移除
        // 减库存
        // 生成订单
        return null;
    }

    // 查看我的订单列表
    @GetMapping("/order")
    public Result orders(@RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                         HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        List<BuyerOrderVO> buyerOrderVOS = buyerOrderService.getOrderList(buyerId, pageNo, pageSize);
        return ResultUtil.success(buyerOrderVOS);
    }

    // 查看订单详情
    @GetMapping("/order/{orderId}")
    public Result getOrder(@PathVariable("orderId") Integer orderId) {
        return ResultUtil.success(buyerOrderService.getOrder(orderId));
    }

    // 支付订单
    @PutMapping("/order/{orderId}/pay")
    public Result payOrder(@PathVariable("orderId") Integer orderId) {
        buyerOrderService.payOrder(orderId);
        return ResultUtil.success();
    }

    // 取消订单
    @PutMapping("/order/{orderId}/cancel")
    public Result cancelOrder(@PathVariable("orderId") Integer orderId) {
        buyerOrderService.cancelOrder(orderId);
        return ResultUtil.success();
    }

    // 确认收货
    @PutMapping("/order/{orderId}/confirm")
    public Result confirmOrderReceived(@PathVariable("orderId") Integer orderId) {
        buyerOrderService.confirmOrder(orderId);
        return ResultUtil.success();
    }

}
