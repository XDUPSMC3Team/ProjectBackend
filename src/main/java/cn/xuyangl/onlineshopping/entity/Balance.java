package cn.xuyangl.onlineshopping.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_BALANCE")
@Data
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer balance;
}
