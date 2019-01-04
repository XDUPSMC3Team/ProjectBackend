package cn.xuyangl.onlineshopping.model;

import lombok.Data;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2019/1/4 20:43
 */
@Data
public class WithdrawData {
    private Integer shopId;
    private String accountId;   //提款账户
    private Double money;   //  提款金额
}
