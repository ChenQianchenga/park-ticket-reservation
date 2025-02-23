package com.ticket.controller.user;

import com.ticket.result.R;
import com.ticket.service.ParkReservationConfigService;
import com.ticket.vo.ParkReservationConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userParkReservationConfigController")
@RequestMapping("/user/park")
@Slf4j
public class ParkReservationConfigController {
    @Autowired
    private ParkReservationConfigService parkReservationConfigService;

    // 根据ID查询游乐园配置及时间段
    @GetMapping("/config/{id}")
    public R<ParkReservationConfigVO> getConfigWithPeriods(@PathVariable(value = "id", required = false) Long id) {
        ParkReservationConfigVO parkReservationConfigVO = parkReservationConfigService.getConfigWithPeriods(id);
        return R.success(parkReservationConfigVO);
    }
}
