package com.ticket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {
    //主键
    private Long id;
    //用户名
    private String username;
    //手机号
    private String phone;
    //token
    private String authentication;

}
