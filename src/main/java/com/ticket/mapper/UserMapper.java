package com.ticket.mapper;

import com.github.pagehelper.Page;
import com.ticket.dto.UserPageQueryDTO;
import com.ticket.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void save(User user);
    //根据手机号来查询用户
    User getByPhone(String phone);

    Page<User> PageQuery(UserPageQueryDTO userPageQueryDTO);

    List<User> getAllUsers();
}
