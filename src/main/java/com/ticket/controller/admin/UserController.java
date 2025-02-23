package com.ticket.controller.admin;

import com.ticket.dto.UserPageQueryDTO;
import com.ticket.entity.User;
import com.ticket.result.PageResult;
import com.ticket.result.R;
import com.ticket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    //分页查询所有用户
    @GetMapping("/page")
    public R<PageResult> PageQuery(UserPageQueryDTO userPageQueryDTO) {
        log.info("分页查询用户：{}",userPageQueryDTO);
        PageResult pageResult = userService.PageQuery(userPageQueryDTO);
        return R.success(pageResult);
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportCSV(@RequestParam String fileName) throws IOException {
        // 获取用户数据
        List<User> users = userService.getAllUsers();

        // 将数据转换为CSV格式
        List<String[]> data = users.stream()
                .map(user -> new String[]{String.valueOf(user.getId()), user.getUsername(), String.valueOf(user.getPhone()),String.valueOf(user.getCreateTime())})
                .collect(Collectors.toList());

        // 设置表头
        data.add(0, new String[]{"ID", "姓名", "手机号","注册时间"});

        // 将数据写入到内存中的字节流
        ByteArrayInputStream byteArrayInputStream = userService.writeDataToCSV(data);
        // URL编码文件名，防止中文乱码
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"); // 处理空格问题
        // 设置响应头，告知浏览器下载文件
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv;charset=UTF-8");  // 设置文件编码为UTF-8

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(byteArrayInputStream));
    }

}
