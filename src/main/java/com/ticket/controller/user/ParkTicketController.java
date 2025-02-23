package com.ticket.controller.user;

import com.ticket.dto.ParkTicketPageQueryDTO;
import com.ticket.entity.ParkTicket;
import com.ticket.result.PageResult;
import com.ticket.result.R;
import com.ticket.service.ParkTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userParkTicketController")
@RequestMapping("/user/ticket")
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

    @GetMapping("/all")
    public R<List<ParkTicket>> getAllTickets() {
        log.info("获取所有门票信息");
        List<ParkTicket> tickets = parkTicketService.getAllTickets();
        return R.success(tickets);
    }

}
