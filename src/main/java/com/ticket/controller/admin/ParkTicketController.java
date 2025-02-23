package com.ticket.controller.admin;

import com.ticket.dto.ParkTicketPageQueryDTO;
import com.ticket.entity.Announcement;
import com.ticket.entity.ParkTicket;
import com.ticket.entity.User;
import com.ticket.result.PageResult;
import com.ticket.result.R;
import com.ticket.service.ParkTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/ticket")
@Slf4j
public class ParkTicketController {
    @Autowired
    private ParkTicketService parkTicketService;

    //添加门票
    @PostMapping
    public R<String> add(@RequestBody ParkTicket parkTicket) {
        log.info("新增门票：{}", parkTicket);
        parkTicketService.add(parkTicket);
        return R.success();
    }

    @GetMapping("/page")
    public R<PageResult> page(ParkTicketPageQueryDTO parkTicketPageQueryDTO) {
        log.info("门票信息分页查询:{}", parkTicketPageQueryDTO);
        PageResult page = parkTicketService.pageQuery(parkTicketPageQueryDTO);
        return R.success(page);
    }

    //公告状态切换
    @PostMapping("status/{status}")
    public R<String> startOrStop(@PathVariable Integer status, @RequestParam Long id) {
        log.info("起售停售门票状态：{}，{}", status, id);

        parkTicketService.startOrStop(status, id);
        return R.success("状态更新成功");
    }

    @DeleteMapping
    public R<String> deleteById(Long id) {
        log.info("删除门票:{}", id);
        parkTicketService.deleteById(id);
        return R.success("公告删除成功");
    }

    @PutMapping
    public R<String> update(@RequestBody ParkTicket parkTicket) {
        log.info("修改门票:{}", parkTicket);
        parkTicketService.update(parkTicket);
        return R.success("门票修改成功");
    }

//    @GetMapping("/export")
//    public ResponseEntity<InputStreamResource> exportCSV(@RequestParam String fileName) throws IOException {
//        // 获取用户数据
//        List<User> users = userService.getAllUsers();
//
//        // 将数据转换为CSV格式
//        List<String[]> data = users.stream()
//                .map(user -> new String[]{String.valueOf(user.getId()), user.getUsername(), String.valueOf(user.getPhone()),String.valueOf(user.getCreateTime())})
//                .collect(Collectors.toList());
//
//        // 设置表头
//        data.add(0, new String[]{"ID", "姓名", "手机号","注册时间"});
//
//        // 将数据写入到内存中的字节流
//        ByteArrayInputStream byteArrayInputStream = userService.writeDataToCSV(data);
//        // URL编码文件名，防止中文乱码
//        String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"); // 处理空格问题
//        // 设置响应头，告知浏览器下载文件
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");
//        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv;charset=UTF-8");  // 设置文件编码为UTF-8
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(new InputStreamResource(byteArrayInputStream));
//    }
}
