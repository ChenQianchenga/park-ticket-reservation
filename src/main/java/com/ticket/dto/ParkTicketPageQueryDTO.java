package com.ticket.dto;

import lombok.Data;

@Data
public class ParkTicketPageQueryDTO {
    private String ticketType;
    //页码
    private int pageNum;
    //每页显示记录数
    private int pageSize;
}
