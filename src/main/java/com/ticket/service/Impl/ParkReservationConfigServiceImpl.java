package com.ticket.service.Impl;

import com.ticket.entity.ParkOpeningPeriod;
import com.ticket.entity.ParkReservationConfig;
import com.ticket.mapper.ParkOpeningPeriodMapper;
import com.ticket.mapper.ParkReservationConfigMapper;
import com.ticket.service.ParkReservationConfigService;
import com.ticket.vo.ParkReservationConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class ParkReservationConfigServiceImpl implements ParkReservationConfigService {
    @Autowired
    private ParkReservationConfigMapper parkReservationConfigMapper;

    @Autowired
    private ParkOpeningPeriodMapper parkOpeningPeriodMapper;

    @Override
    public ParkReservationConfigVO getConfigWithPeriods(Long id) {

        return parkReservationConfigMapper.getConfigWithPeriods(id);
    }
    @Transactional
    public void updateConfigWithPeriods(ParkReservationConfigVO parkReservationConfigVO) {
        // 更新 park_reservation_config 配置
        ParkReservationConfig parkReservationConfig = new ParkReservationConfig();
        parkReservationConfig.setId(parkReservationConfigVO.getId());
        parkReservationConfig.setParkName(parkReservationConfigVO.getParkName());
        parkReservationConfig.setOpenTime(parkReservationConfigVO.getOpenTime());
        parkReservationConfig.setCloseTime(parkReservationConfigVO.getCloseTime());
        parkReservationConfig.setDailyLimit(parkReservationConfigVO.getDailyLimit());
        parkReservationConfig.setMaxAdvanceDays(parkReservationConfigVO.getMaxAdvanceDays());
        parkReservationConfigMapper.updateById(parkReservationConfig);

        // 删除现有的时间段（如果有）
        parkOpeningPeriodMapper.deleteByParkConfigId(parkReservationConfigVO.getId());

        // 更新新的时间段
        if (parkReservationConfigVO.getOpeningPeriods() != null && !parkReservationConfigVO.getOpeningPeriods().isEmpty()) {
            for (ParkOpeningPeriod period : parkReservationConfigVO.getOpeningPeriods()) {
                period.setParkConfigId(parkReservationConfigVO.getId());
                parkReservationConfig.setCreateTime(LocalDateTime.now());
                parkReservationConfig.setUpdateTime(LocalDateTime.now());
                parkOpeningPeriodMapper.insert(period);
            }
        }
    }
}
