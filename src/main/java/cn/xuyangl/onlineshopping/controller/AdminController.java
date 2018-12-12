package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.entity.Admin;
import cn.xuyangl.onlineshopping.model.LoginForm;
import cn.xuyangl.onlineshopping.service.AdminService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public Result login(LoginForm loginForm, HttpSession session) {
        Admin adminInDB = adminService.findByUsername(loginForm.getUsername());
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

    @PostMapping("/personal/approve")
    public Result approve(Integer shopId, HttpSession session) {
        if (!adminService.approveShop(shopId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }

    @PostMapping("/personal/reject")
    public Result reject(Integer shopId, HttpSession session) {
        if (!adminService.rejectShop(shopId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }

    @GetMapping("/personal/search")
    public Result search(Integer shopId, HttpSession session) {
        return ResultUtil.success(adminService.findShop(shopId));
    }

    @PostMapping("/personal/close")
    public Result closeShop(Integer shopId, HttpSession session) {
        if (!adminService.closeShop(shopId)) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success();
    }
}
