package com.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketReservationPageQueryDTO {
    private String phoneNumber;

    private Long userId; // 关联的用户ID
    //页码
    private int pageNum;
    //每页显示记录数
    private int pageSize;
}
