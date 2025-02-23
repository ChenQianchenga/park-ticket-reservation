package com.ticket.mapper;

import com.ticket.entity.ParkOpeningPeriod;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkOpeningPeriodMapper {
    void deleteByParkConfigId(Long id);

    void insert(ParkOpeningPeriod period);
}
