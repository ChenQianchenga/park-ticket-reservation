package com.ticket.vo;

import com.ticket.entity.ParkOpeningPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkReservationConfigVO implements Serializable {
    private Long id;
    private String parkName;
    private Time openTime;
    private Time closeTime;
    private Integer dailyLimit;
    private Integer maxAdvanceDays;
    private List<ParkOpeningPeriod> openingPeriods = new ArrayList<>();  // 时间段配置
}
