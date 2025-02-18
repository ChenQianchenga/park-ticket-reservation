package com.ticket.reservation.controller.user;

import com.ticket.reservation.dto.UserDto;
import com.ticket.reservation.result.R;
import com.ticket.reservation.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody UserDto user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
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
    public String createUser(@RequestBody UserDto user) {
        log.info("Creating new user: {}", user);
        // 这里可以处理创建用户的逻辑
        return "User created: " + user;
    }
}
