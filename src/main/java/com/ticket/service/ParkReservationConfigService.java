package com.ticket.service;

import com.ticket.entity.ParkReservationConfig;
import com.ticket.vo.ParkReservationConfigVO;
import org.springframework.stereotype.Service;

@Service
public interface ParkReservationConfigService {
    ParkReservationConfigVO getConfigWithPeriods(Long id);

    void updateConfigWithPeriods(ParkReservationConfigVO parkReservationConfigVO);
}
