package cn.xuyangl.onlineshopping.interceptor;

import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.exception.NoPermissionException;
import cn.xuyangl.onlineshopping.utils.JwtToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/8 17:59
 */
@Component
public class SellerInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        if (!(session.getAttribute(Common.USER_TYPE).equals("seller")||session.getAttribute(Common.USER_TYPE).equals("admin")) || session.getAttribute(Common.BUYER_ID) == null) {
            throw new NoPermissionException("you haven't login as a seller.");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
