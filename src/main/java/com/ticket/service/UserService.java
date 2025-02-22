package com.ticket.service;

import com.ticket.dto.UserDTO;
import com.ticket.dto.UserPageQueryDTO;
import com.ticket.entity.User;
import com.ticket.result.PageResult;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/*
用户注册操作
 */
@Service
public interface UserService {
    //添加用户
    void save(UserDTO userDto);
    //用户登录
    User login(UserDTO userDto);

    PageResult PageQuery(UserPageQueryDTO userPageQueryDTO);

    List<User> getAllUsers();

    ByteArrayOutputStream generateUserExcel() throws IOException;

    ByteArrayInputStream writeDataToCSV(List<String[]> data) throws IOException;
}
