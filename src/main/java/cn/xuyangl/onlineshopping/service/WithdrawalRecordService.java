package cn.xuyangl.onlineshopping.service;

import cn.xuyangl.onlineshopping.entity.WithdrawalRecord;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2019/1/4 21:03
 */
public interface WithdrawalRecordService {
    void addWithdrawalRecord(WithdrawalRecord withdrawalRecord);
    List<WithdrawalRecord> viewWithdrawalRecord(String shopName);
}
