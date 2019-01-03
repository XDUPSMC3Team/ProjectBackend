package cn.xuyangl.onlineshopping.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_EXCHANGE_RATE")
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    private String exchangeRate;
}
