package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.dao.WithdrawalRecordDao;
import cn.xuyangl.onlineshopping.entity.WithdrawalRecord;
import cn.xuyangl.onlineshopping.service.WithdrawalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2019/1/4 21:04
 */
@Service
public class WithdrawalRecordImpl implements WithdrawalRecordService{

    @Autowired
    private WithdrawalRecordDao withdrawalRecordDao;

    /**
     *  增加一条记录
     * @param withdrawalRecord
     */
    @Override
    public void addWithdrawalRecord(WithdrawalRecord withdrawalRecord) {
        withdrawalRecordDao.save(withdrawalRecord);
    }


    @Override
    public List<WithdrawalRecord> viewWithdrawalRecord(String shopName) {
        return withdrawalRecordDao.findAllByName(shopName);
    }
}
