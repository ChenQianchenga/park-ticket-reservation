package com.ticket.service;

import com.ticket.dto.UserDto;
import com.ticket.entity.User;
import org.springframework.stereotype.Service;
/*
用户注册操作
 */
@Service
public interface UserService {
    //添加用户
    void save(UserDto userDto);
    //用户登录
    User login(UserDto userDto);
}
