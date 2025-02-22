package com.ticket.controller.user;

import com.ticket.constant.JwtClaimsConstant;
import com.ticket.properties.JwtProperties;
import com.ticket.dto.UserDTO;
import com.ticket.entity.User;
import com.ticket.result.R;
import com.ticket.service.UserService;
import com.ticket.utils.JwtUtil;
import com.ticket.utils.ValidateCodeUtils;
import com.ticket.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController("userUserController")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties; //jwt令牌相关配置类

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody UserDTO userDto, HttpSession session) {
        //获取手机号
        String phone = userDto.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            //调用阿里云提供的短信服务API完成发送短信
            //SMSUtils.sendMessage("瑞吉外卖", "", phone, code);
            //需要将生成的验证码保存到Session
            session.setAttribute(phone, code);
            return R.success("手机验证码短信发送成功");
        }
        return R.error("短信发送失败");
    }

    // 创建新用户
    @PostMapping
    public R<String> add(@RequestBody UserDTO userDto, HttpSession session) {
        log.info("Creating new user: {}", userDto);

        //获取手机号
        String phone = userDto.getPhone();
        //获取验证码
        String code = userDto.getCode();
        //从Session中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);
        //进行验证码对比
        if (codeInSession != null && codeInSession.equals(code)) {
            //对比成功开始注册用户
            userService.save(userDto);

        }
        return R.success();
    }

    @PostMapping("login")
    public R<UserLoginVo> login(@RequestBody UserDTO userDto) {
        log.info("用户登录：用户名：{}，密码：{}", userDto.getPhone(), userDto.getPassword());
        //调用业务登陆返回对象
        User userLogin = userService.login(userDto);
        //设置jwt中有效载荷部分的数据，通常是用户的身份标识
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userLogin.getId());

        //创建jwt令牌
        String authentication = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        //封装响应对象
        UserLoginVo userLoginVo = UserLoginVo.builder()
                .id(userLogin.getId())
                .username(userLogin.getUsername())
                .phone(userLogin.getPhone())
                .authentication(authentication)
                .build();
        log.info("登录成功返回的用户信息:"+userLoginVo);
        return R.success(userLoginVo);
    }

    @PostMapping("/logout")
    public R<String> logout() {
        log.info("用户退出");
        return R.success("退出登录");
    }
}
