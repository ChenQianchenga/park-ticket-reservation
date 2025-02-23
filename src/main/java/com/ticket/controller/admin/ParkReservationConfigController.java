package com.ticket.controller.admin;

import com.ticket.result.R;
import com.ticket.service.ParkReservationConfigService;
import com.ticket.vo.ParkReservationConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/park")
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

    // 修改游乐园配置及时间段
    @PutMapping("/config/{id}")
    public R<String> updateConfigWithPeriods(@PathVariable(value = "id") Long id,
                                             @RequestBody ParkReservationConfigVO parkReservationConfigVO) {
        parkReservationConfigVO.setId(id); // 设置传入的ID，保证更新的是指定的记录
        parkReservationConfigService.updateConfigWithPeriods(parkReservationConfigVO);
        return R.success("配置修改成功");
    }
}
