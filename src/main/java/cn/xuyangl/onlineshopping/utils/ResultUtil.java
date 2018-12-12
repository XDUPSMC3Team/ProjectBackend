package cn.xuyangl.onlineshopping.utils;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;

/**
 * @author xjin
 * created on 2018/11/30 4:59 PM
 */
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     *  根据枚举结果 构造result
     * @param resultEnum
     * @return
     */
    public static  Result error(ResultEnum resultEnum)
    {
        Result result = new Result(resultEnum.code,resultEnum.msg);
        return result;
    }
}
