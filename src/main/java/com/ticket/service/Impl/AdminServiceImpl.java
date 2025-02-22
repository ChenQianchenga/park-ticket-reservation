package com.ticket.service.Impl;

import com.ticket.dto.AdminDTO;
import com.ticket.entity.Admin;
import com.ticket.exception.CustomException;
import com.ticket.mapper.AdminMapper;
import com.ticket.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin login(AdminDTO adminDto) {
        String username = adminDto.getUsername();
        String password = adminDto.getPassword();
        //调用数据库层查询当前用户信息
        Admin admin = adminMapper.getByUsername(username);
        //用户不存在
        if (admin == null){
            throw new CustomException("管理员账号不存在");
        }
        //密码错误
        //对前端传递过来的明文密码进行md5加密处理
//        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(admin.getPassword())){
            throw new CustomException("管理员账号或密码错误");
        }
        return admin;
    }
}
