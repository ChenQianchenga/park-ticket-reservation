package com.ticket.controller.user;

import com.ticket.context.BaseContext;
import com.ticket.dto.TicketReservationPageQueryDTO;
import com.ticket.entity.TicketReservation;
import com.ticket.result.PageResult;
import com.ticket.result.R;
import com.ticket.service.TicketReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userTicketReservationController")
@RequestMapping("/user/reservation")
@Slf4j
public class TicketReservationController {
    @Autowired
    private TicketReservationService ticketReservationService;


    @PostMapping("/create")
    public R sava(@RequestBody TicketReservation ticketReservation){
        log.info("新增预约记录：{}",ticketReservation);
        ticketReservationService.save(ticketReservation);
        return R.success();

    }
    @GetMapping("/page")
    public R<PageResult> PageQuery(TicketReservationPageQueryDTO ticketReservationPageQueryDTO) {
        log.info("查询当前user_id的预约：{}", BaseContext.getCurrentId());
        ticketReservationPageQueryDTO.setUserId(BaseContext.getCurrentId());
        PageResult pageResult = ticketReservationService.PageQuery(ticketReservationPageQueryDTO);
//        System.out.println(pageResult);
        return R.success(pageResult);
    }

    @DeleteMapping
    public R<String> deleteById(@RequestBody TicketReservation ticketReservation) {
        log.info("取消预约:{}", ticketReservation);
        ticketReservationService.deleteById(ticketReservation);
        return R.success("取消预约成功");
    }
}
