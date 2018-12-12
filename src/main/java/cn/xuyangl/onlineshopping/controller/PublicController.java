package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.consts.Common;
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
 */

@RequestMapping("/")
@RestController
public class PublicController {

    /**
     *  登出
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public Result logout(HttpServletResponse response, HttpSession session)
    {
        // 清除session
        session.removeAttribute(Common.USER_TYPE);
        session.removeAttribute(Common.USERNAME);
        if (session.getAttribute(Common.BUYER_ID)!=null)
        {
            session.removeAttribute(Common.BUYER_ID);
        }
        if (session.getAttribute(Common.SELLER_ID)!=null)
        {
            session.removeAttribute(Common.SELLER_ID);
        }
        if (session.getAttribute(Common.ADMIN_ID)!=null)
        {
            session.removeAttribute(Common.ADMIN_ID);
        }
        // 清除cookie
        return ResultUtil.success();
    }

}
