package cn.xuyangl.onlineshopping.VO;

/**
 * @Description 错误结果返回枚举类
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/11/30 17:45
 */
public enum  ResultEnum {

    Success(0, "success"),
    AccountNotFound(1,"account is not found"),
    PasswordIncorrect(2,"password is not correct"),
    RegisterError(3,"register errors"),
    NO_RIGHT(4,"no right"),
    SHOP_NOT_FOUND(5,"the shop not found"),
    OPERATION_FAILURE(6,"operation failed"),
    UPDATE_FAILED(7,"update failed"),
    AD_NOT_EXIST(7,"this advertisement not exists"),
    NOT_FOUND(8,"not found"),
    RegisterEmptyError(9, "username, password, email can't be empty."),
    RegisterUsernameAlreadyExist(10, "the username has been registered."),
    RegisterEmailAlreadyExist(11, "the email has been registered."),
    AttributeKeyNotFound(12,"this attributeKey not found"),
    ProductNotFound(13,"the product is not found"),
    OrderNotExist(14,"this order is not exist"),
    AmountLessThanOne(15, "amount can't be less than 1"),
    EmptyRealNameOrAddress(16, "real name and address can't be empty"),
    OneSellerOnlyHasOneShop(17,"one seller only has one shop"),
    AccountBlocked(18,"Due to your illegal action, your account has been stopped"),
    DateFormatError(19,"date format error"),
    ProductNumberError(20, "please choose at least 1 product to buy."),
    OrderNotReceived(21, "can't comment unreceived order."),
    RepeatedComment(22, "you have comment this order.")
    ;
    public String msg;
    public Integer code;
     ResultEnum(Integer code,String msg)
    {
        this.msg = msg;
        this.code = code;
    }
}
