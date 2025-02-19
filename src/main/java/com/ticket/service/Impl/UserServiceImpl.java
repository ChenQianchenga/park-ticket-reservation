package com.ticket.service.Impl;

import com.ticket.dto.UserDto;
import com.ticket.entity.User;
import com.ticket.mapper.UserMapper;
import com.ticket.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户注册
    public void save(UserDto userDto) {
        User user = new User();
        //对象的拷贝
        BeanUtils.copyProperties(userDto,user);
        //需要进行md5加密处理
        user.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        //设置用户创建时间
        user.setCreateTime(LocalDateTime.now());
        //设置用户修改时间
        user.setUpdateTime(LocalDateTime.now());

        userMapper.save(user);


    }
}
