package com.ticket.reservation.entity;

import lombok.Data;

import java.time.LocalDateTime;

/*
用户类
 */
@Data
public class User {
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;

    //创建时间
    private LocalDateTime createTime;


    //更新时间
    private LocalDateTime updateTime;
}
