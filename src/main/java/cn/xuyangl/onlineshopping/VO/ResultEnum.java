package cn.xuyangl.onlineshopping.VO;

/**
 * @Description 错误结果返回枚举类
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/30 17:45
 */
public enum  ResultEnum {

    AccountNotFound(1,"account is not found"),
    PasswordIncorrect(2,"password is not correct"),
    RegisterError(3,"register errors")
    ;
    public String msg;
    public Integer code;
     ResultEnum(Integer code,String msg)
    {
        this.msg = msg;
        this.code = code;
    }
}
