package cn.xuyangl.onlineshopping.interceptor;

import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.exception.NoPermissionException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xjin
 * created on 2018/12/8 3:48 PM
 */
@Component
public class BuyerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute(Common.USER_TYPE) != "buyer" || session.getAttribute(Common.BUYER_ID) == null) {
            throw new NoPermissionException("you haven't login as a buyer.");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
