package cn.xuyangl.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_WITHDRAW_HISTORY")
@Data
public class WithdrawHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String alipayId;

    private Integer count;

    private String adminName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;   //创建时间
}
