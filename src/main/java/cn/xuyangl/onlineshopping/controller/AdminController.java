package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.OrderVO;
import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.dao.BuyerDao;
import cn.xuyangl.onlineshopping.entity.Admin;
import cn.xuyangl.onlineshopping.entity.Buyer;
import cn.xuyangl.onlineshopping.model.LoginForm;
import cn.xuyangl.onlineshopping.service.AdminService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public Result register(@RequestBody Admin admin) {
        if (admin.getUsername() == null || admin.getPassword() == null || admin.getEmail() == null) {
            return ResultUtil.error(ResultEnum.RegisterEmptyError);
        }
        if (adminService.register(admin)) {
            return ResultUtil.success("Welcome, " + admin.getUsername());
        } else {
            return ResultUtil.error(1, "the username you detected has been register.");
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpSession session) {
        Admin adminInDB = adminService.findByUsername(loginForm.getUsername());
        System.out.println(loginForm.getUsername());
        if (adminInDB == null) return ResultUtil.error(1, "Admin account isn't exist.");
        Admin admin = adminService.login(loginForm.getUsername(), loginForm.getPassword());
        if (admin == null) return ResultUtil.error(2, "password uncorrect.");
        session.setAttribute(Common.USER_TYPE, "admin");
        session.setAttribute(Common.ADMIN_ID, admin.getId());
        session.setAttribute(Common.USERNAME, admin.getUsername());
        session.setMaxInactiveInterval(3600);
        return ResultUtil.success();
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute(Common.USER_TYPE);
        session.removeAttribute(Common.ADMIN_ID);
        session.removeAttribute(Common.USERNAME);
        return ResultUtil.success();
    }

    @GetMapping("/personal/appliedShops")
    public Result appliedShops(HttpSession session) {
        return ResultUtil.success(adminService.newAppliedShops());
    }

    @PostMapping("/personal/approve/{shopId}")
    public Result approve(@PathVariable(name = "shopId") Integer shopId, HttpSession session) {
        if (!adminService.approveShop(shopId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }

    @PostMapping("/personal/reject/{shopId}")
    public Result reject(@PathVariable(name = "shopId") Integer shopId, HttpSession session) {
        if (!adminService.rejectShop(shopId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }

    @GetMapping("/personal/search/{shopName}")
    public Result search(@PathVariable("shopName") String shopName, HttpSession session) {
        List result = adminService.findShopByName(shopName);
        if (result == null || result.size() == 0) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success(result);
    }

    @PostMapping("/personal/close/{shopId}")
    public Result closeShop(@PathVariable(name = "shopId") Integer shopId, HttpSession session) {
        if (!adminService.closeShop(shopId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }

    @GetMapping("/personal/order/search/{orderId}")
    public Result searchOrder(@PathVariable("orderId") Integer orderId, HttpSession session) {
        OrderVO vo = adminService.findOrderById(orderId);
        if (vo == null) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success(vo);
    }

    @GetMapping("/personal/customer/search/{username}")
    public Result searchUser(@PathVariable("username") String username, HttpSession session) {
        Buyer buyer = adminService.findCustomerByUsername(username);
        if (buyer == null) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success(buyer);
    }

    @PostMapping("/personal/product/off/{productId}")
    public Result offShelves(@PathVariable("productId") Integer productId, HttpSession session) {
        if (adminService.offShelves(productId)) {
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.NOT_FOUND);
    }

    @PostMapping("/personal/customer/block/{userId}")
    public Result blockUser(@PathVariable("userId") Integer userId, HttpSession session) {
        if (!adminService.blockUser(userId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }

    @PostMapping("/personal/seller/block/{sellerId}")
    public Result blockSeller(@PathVariable("sellerId") Integer sellerId, HttpSession session) {
        if (!adminService.blockSeller(sellerId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }

    @GetMapping("/personal/biddingInfo")
    public Result biddingInfo() {
        return ResultUtil.success(adminService.biddingInfo());
    }

    @GetMapping("/personal/saleHistory")
    public Result saleHistory() {
        return ResultUtil.success(adminService.saleHistory());
    }

    @GetMapping("/personal/exchangeRate")
    public Result exchangeRate() {
        return ResultUtil.success(adminService.exchangeRate());
    }

    @PostMapping("/personal/changeExchangeRate/{rate}")
    public Result changeExchangeRate(@PathVariable("rate") String rate) {
        adminService.changeExchangeRate(rate + "%");
        return ResultUtil.success();
    }

    @GetMapping("/personal/incomeData")
    public Result getIncomeData(@RequestParam(value = "date", required = false) String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date baseDate = null;
        if (date==null||"".equals(date))
        {
            baseDate = new Date();
        }else{
            try {
                baseDate = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                return ResultUtil.error(ResultEnum.DateFormatError);
            }
        }
        return ResultUtil.success(adminService.getIncomeData(baseDate));
    }

    @GetMapping("/personal/balance")
    public Result getBalance() {
        return ResultUtil.success(adminService.getBalance());
    }

    @PostMapping("/personal/withdraw/{alipayId}/{money}")
    public Result withDraw(@PathVariable("alipayId") String alipayId, @PathVariable("money") String money) {
        if (adminService.withdraw(Integer.parseInt(money), alipayId)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(ResultEnum.BalanceNotEnough);
        }
    }
}
