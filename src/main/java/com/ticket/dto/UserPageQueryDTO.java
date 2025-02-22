package com.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageQueryDTO {
    private String username;
    //页码
    private int pageNum;
    //每页显示记录数
    private int pageSize;
}
