package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xjin
 * created on 2018/11/30 5:27 PM
 *
 * 不需要登录即可使用的功能，比如普通的浏览搜索等。
 */

@RequestMapping("/")
@RestController
public class PublicController {

    public static final String USER_TYPE = "userType";
    public static final String BUYER_ID = "buyerId";
    public static final String USERNAME = "username";
    /**
     *  登出
     * @param cookie
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public Result logout(@CookieValue(name = "token")Cookie cookie, HttpServletResponse response, HttpSession session)
    {
        // 清除session
        session.removeAttribute(USER_TYPE);
        session.removeAttribute(BUYER_ID);
        session.removeAttribute(USERNAME);
        // 清除cookie
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResultUtil.success();
    }

}
