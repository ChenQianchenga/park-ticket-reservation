package com.ticket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
用户类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String username;
    private String password;
    private String phone;

    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
}
