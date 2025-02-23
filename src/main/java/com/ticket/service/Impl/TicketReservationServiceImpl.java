package com.ticket.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ticket.context.BaseContext;
import com.ticket.dto.TicketReservationPageQueryDTO;
import com.ticket.entity.ParkReservationConfig;
import com.ticket.entity.ParkTicket;
import com.ticket.entity.TicketReservation;
import com.ticket.exception.CustomException;
import com.ticket.mapper.ParkOpeningPeriodMapper;
import com.ticket.mapper.ParkReservationConfigMapper;
import com.ticket.mapper.ParkTicketMapper;
import com.ticket.mapper.TicketReservationMapper;
import com.ticket.result.PageResult;
import com.ticket.service.TicketReservationService;
import com.ticket.vo.ParkReservationConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TicketReservationServiceImpl implements TicketReservationService {
    @Autowired
    private ParkTicketMapper parkTicketMapper;

    @Autowired
    private ParkReservationConfigMapper parkReservationConfigMapper;

    @Autowired
    private TicketReservationMapper ticketReservationMapper;


    @Transactional
    public void save(TicketReservation ticketReservation) {
        //先查一下用户下单的票库存还有没
        ParkTicket parkTicket = parkTicketMapper.getByType(ticketReservation.getTicketType());
        //拿到当前票的库存
        int parkTicketStock = parkTicket.getAvailableStock();
        log.info("当前{}的库存为{}",ticketReservation.getTicketType(),parkTicketStock);
        if (parkTicketStock<=0){
            throw new CustomException(String.format("库存不足：%s", ticketReservation.getTicketType().toString()));
        }
        //再查一下当天预约的人数是否有超过配置
        Long id = 1L;
        ParkReservationConfigVO parkReservationConfigVO = parkReservationConfigMapper.getConfigWithPeriods(id);
        log.info("配置最大是{}",parkReservationConfigVO.getDailyLimit());
        //当天已经预约人数
        Integer userReservationCount = ticketReservationMapper.countByDate(ticketReservation.getVisitDate());
        log.info("当天预约的人数为：{}",userReservationCount);
        if (userReservationCount>=parkReservationConfigVO.getDailyLimit()){
            throw new CustomException(String.format("%s:天已达到最大预约人数:%s人",ticketReservation.getVisitDate(),userReservationCount.toString()));
        }
        //完成保存
        ticketReservation.setUserId(BaseContext.getCurrentId());
        ticketReservation.setCreateTime(LocalDateTime.now());
        ticketReservation.setUpdateTime(LocalDateTime.now());
        ticketReservationMapper.save(ticketReservation);
        //库存-1
        parkTicket.setAvailableStock(parkTicketStock-1);
        parkTicketMapper.update(parkTicket);

    }

    public PageResult PageQuery(TicketReservationPageQueryDTO ticketReservationPageQueryDTO) {
        //分页插件自动填充分页命令
        PageHelper.startPage(ticketReservationPageQueryDTO.getPageNum(), ticketReservationPageQueryDTO.getPageSize());
        Page<TicketReservation> page = ticketReservationMapper.PageQuery(ticketReservationPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public void deleteById(TicketReservation ticketReservation) {
        //删掉记录
        ticketReservationMapper.deleteById(ticketReservation.getReservationId());
        //库存+1
        //先查一下用户下单的票库存还有没
        ParkTicket parkTicket = parkTicketMapper.getByType(ticketReservation.getTicketType());
        int parkTicketStock = parkTicket.getAvailableStock();
        parkTicket.setAvailableStock(parkTicketStock+1);
        parkTicketMapper.update(parkTicket);

    }

    public void update(TicketReservation ticketReservation) {
        ticketReservationMapper.update(ticketReservation);
    }
}
