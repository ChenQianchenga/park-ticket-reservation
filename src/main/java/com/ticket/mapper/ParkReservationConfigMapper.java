package com.ticket.mapper;

import com.ticket.entity.ParkReservationConfig;
import com.ticket.vo.ParkReservationConfigVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkReservationConfigMapper {

    ParkReservationConfigVO getConfigWithPeriods(Long id);

    void updateById(ParkReservationConfig parkReservationConfig);
}
