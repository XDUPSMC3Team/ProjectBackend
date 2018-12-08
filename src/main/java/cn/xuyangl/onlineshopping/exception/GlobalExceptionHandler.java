package cn.xuyangl.onlineshopping.exception;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xjin
 * created on 2018/12/8 4:11 PM
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletResponse response, Exception e) {
        response.setStatus(200);
        return ResultUtil.error(1, e.getMessage());
    }
}
