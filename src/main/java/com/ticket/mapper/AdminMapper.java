package com.ticket.mapper;

import com.ticket.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin getByUsername(String username);
}
