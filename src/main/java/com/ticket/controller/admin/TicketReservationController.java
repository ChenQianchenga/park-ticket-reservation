package com.ticket.controller.admin;

import com.ticket.context.BaseContext;
import com.ticket.dto.TicketReservationPageQueryDTO;
import com.ticket.entity.TicketReservation;
import com.ticket.mapper.TicketReservationMapper;
import com.ticket.result.PageResult;
import com.ticket.result.R;
import com.ticket.service.TicketReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/reservation")
@Slf4j
public class TicketReservationController {

    @Autowired
    private TicketReservationService ticketReservationService;

    @GetMapping("/page")
    public R<PageResult> PageQuery(TicketReservationPageQueryDTO ticketReservationPageQueryDTO) {
        log.info("查询所有用户的预约：{}", ticketReservationPageQueryDTO);
        PageResult pageResult = ticketReservationService.PageQuery(ticketReservationPageQueryDTO);
        return R.success(pageResult);
    }

    @DeleteMapping
    public R<String> deleteById(@RequestBody TicketReservation ticketReservation) {
        log.info("取消预约:{}", ticketReservation);
        ticketReservationService.deleteById(ticketReservation);
        return R.success("取消预约成功");
    }

    @PutMapping
    public R<String> update(@RequestBody TicketReservation ticketReservation) {
        log.info("修改预约:{}", ticketReservation);
        ticketReservationService.update(ticketReservation);
        return R.success("修改成功");
    }




}
