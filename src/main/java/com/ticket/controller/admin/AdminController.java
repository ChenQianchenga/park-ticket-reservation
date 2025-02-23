package com.ticket.controller.admin;

import com.ticket.constant.JwtClaimsConstant;
import com.ticket.dto.AdminDTO;
import com.ticket.entity.Admin;
import com.ticket.properties.JwtProperties;
import com.ticket.result.R;
import com.ticket.service.AdminService;
import com.ticket.utils.JwtUtil;
import com.ticket.vo.AdminLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtProperties jwtProperties; //jwt令牌相关配置类

    @PostMapping("/login")
    public R<AdminLoginVO> login(@RequestBody AdminDTO adminDto) {
        log.info("管理员登陆：用户名：{}，密码：{}", adminDto.getUsername(), adminDto.getPassword());
        //调用业务登陆返回对象
        Admin adminLogin = adminService.login(adminDto);
        //设置jwt中有效载荷部分的数据，通常是用户的身份标识
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, adminLogin.getId());

        //创建jwt令牌
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        //封装响应对象
        AdminLoginVO adminLoginVo = AdminLoginVO.builder()
                .id(adminLogin.getId())
                .username(adminLogin.getUsername())
                .password(adminLogin.getPassword())
                .token(token)
                .build();
        return R.success(adminLoginVo);
    }

    @PostMapping("/logout")
    public R<String> logout() {
        log.info("管理员退出");
        return R.success("退出登录");
    }

}
