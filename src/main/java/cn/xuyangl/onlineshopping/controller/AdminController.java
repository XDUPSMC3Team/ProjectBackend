package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.entity.Admin;
import cn.xuyangl.onlineshopping.service.AdminService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final static String USER_TYPE = "userType";
    private final static String ADMIN_ID = "adminId";
    private final static String USERNAME = "username";

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public Result register(String email, String username, String password) {
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setUsername(username);
        admin.setPassword(password);
        if (adminService.register(admin)) {
            return ResultUtil.success("Welcome, " + username);
        } else {
            return ResultUtil.error(1, "the username you detected has been register.");
        }
    }

    @PostMapping("/login")
    public Result login(String username, String password, HttpSession session) {
        Admin adminInDB = adminService.findByUsername(username);
        if (adminInDB == null) return ResultUtil.error(1, "Admin account isn't exist.");
        Admin Admin = adminService.login(username, password);
        if (Admin == null) return ResultUtil.error(2, "password uncorrect.");
        session.setAttribute(USER_TYPE, "Admin");
        session.setAttribute(ADMIN_ID, Admin.getId());
        session.setAttribute(USERNAME, Admin.getUsername());
        session.setMaxInactiveInterval(3600);
        return ResultUtil.success();
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute(USER_TYPE);
        session.removeAttribute(ADMIN_ID);
        session.removeAttribute(USERNAME);
        return ResultUtil.success();
    }

    @GetMapping("/appliedShops")
    public Result appliedShops() {
        return ResultUtil.success(adminService.newAppliedShops());
    }
}
