package com.ticket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketReservation {
    private Integer reservationId; // 唯一标识预约记录

    private Long userId; // 关联的用户ID

    private LocalDate visitDate; // 参观日期

    private String visitTime; // 参观时间（例如“13:00 - 17:00”）

    private String ticketType; // 门票类型（例如“成人票”）

    private String visitorName; // 游客姓名

    private String identityCardNumber; // 身份证号

    private String phoneNumber; // 手机号码


    private BigDecimal paymentAmount; // 支付金额

    private Integer status = 1; // 预约状态：1 未使用, 2 已使用, 3 已过期

    private LocalDateTime createTime; // 创建时间

    private LocalDateTime updateTime; // 更新时间
}
