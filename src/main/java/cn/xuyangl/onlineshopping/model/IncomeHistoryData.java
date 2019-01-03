package cn.xuyangl.onlineshopping.model;

import lombok.Data;

import java.util.List;

/**
 * @Description 历史收入
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/29 12:02
 */
@Data
public class IncomeHistoryData {

    private Double daily;
    private Double weekly;
    private Double monthly;
    private Double yearly;

    public IncomeHistoryData() {
        daily = 0.0;
        weekly = 0.0;
        monthly = 0.0;
        yearly = 0.0;
    }

    public void mergeFromAnother(IncomeHistoryData another) {
        daily += another.daily;
        weekly += another.weekly;
        monthly += another.monthly;
        yearly += another.yearly;
    }

}
