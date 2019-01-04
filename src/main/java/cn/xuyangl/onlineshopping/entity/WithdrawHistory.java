package cn.xuyangl.onlineshopping.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_WITHDRAW_HISTORY")
@Data
public class WithdrawHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String alipayId;

    private Integer count;
}
