package com.ticket.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.opencsv.CSVWriter;
import com.ticket.dto.UserDTO;
import com.ticket.dto.UserPageQueryDTO;
import com.ticket.entity.User;
import com.ticket.exception.CustomException;
import com.ticket.mapper.UserMapper;
import com.ticket.result.PageResult;
import com.ticket.service.UserService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户注册
    public void save(UserDTO userDto) {
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

    @Override
    public User login(UserDTO userDto) {
        String phone = userDto.getPhone();
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        //调用数据库查询当前用户信息
        User user = userMapper.getByPhone(phone);
        //用户不存在
        if (user == null){
            //抛出自定义的异常
            throw new CustomException("用户不存在");
        }
        //密码错误
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!pwd.equals(user.getPassword())){
            //抛出密码错误异常
            throw new CustomException("密码错误");
        }
        return user;
    }

    public PageResult PageQuery(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPageNum(),userPageQueryDTO.getPageSize());
        Page<User> page = userMapper.PageQuery(userPageQueryDTO);
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public ByteArrayInputStream writeDataToCSV(List<String[]> data) throws IOException {
        // 使用ByteArrayOutputStream将CSV写入内存
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {
            // 写入BOM头，确保Excel可以识别UTF-8编码
            byteArrayOutputStream.write(0xEF);
            byteArrayOutputStream.write(0xBB);
            byteArrayOutputStream.write(0xBF);
            // 使用CSVWriter写入数据
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeAll(data);
            csvWriter.close();

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
    }

}
