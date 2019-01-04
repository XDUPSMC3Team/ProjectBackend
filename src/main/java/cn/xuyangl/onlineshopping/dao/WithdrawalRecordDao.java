package cn.xuyangl.onlineshopping.dao;

import cn.xuyangl.onlineshopping.entity.WithdrawalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2019/1/4 21:05
 */
@Repository
public interface WithdrawalRecordDao extends JpaRepository<WithdrawalRecord,Integer>{


    /**
     *  根据名称查询提款历史
     */

    List<WithdrawalRecord> findAllByName(String name);
}
