package cn.xuyangl.onlineshopping.interceptor;

import cn.xuyangl.onlineshopping.exception.SellerException;
import cn.xuyangl.onlineshopping.utils.JwtToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie:cookies)
        {
            if (cookie.getName().equals("token"))
            {
                // 说明cookie 存在
                String type = JwtToken.verifyToken(cookie.getValue()).get("type").asString();
                if (!type.equals("seller"))
                {
                    throw new SellerException("you haven't login as a seller.");
                }else{
                    return true;
                }
            }
        }
        throw new SellerException("you haven't login");
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
