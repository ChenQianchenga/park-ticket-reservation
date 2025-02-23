package com.ticket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkTicket {
    private Long id;
    private String ticketType; //类型
    private BigDecimal price; // 票价

    private Integer availableStock; // 可售库存

    private LocalDate startDate; // 门票有效开始日期

    private LocalDate endDate; // 门票有效结束日期

    private int status; // 门票状态 0停售，1在售

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
