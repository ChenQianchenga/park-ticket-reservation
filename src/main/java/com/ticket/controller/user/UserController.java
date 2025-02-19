package com.ticket.controller.user;

import com.ticket.dto.UserDto;
import com.ticket.result.R;
import com.ticket.service.UserService;
import com.ticket.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody UserDto userDto, HttpSession session) {
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
    public R<String> add(@RequestBody UserDto userDto, HttpSession session) {
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
}
