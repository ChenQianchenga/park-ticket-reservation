package com.ticket.mapper;

import com.ticket.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void save(User user);
}
