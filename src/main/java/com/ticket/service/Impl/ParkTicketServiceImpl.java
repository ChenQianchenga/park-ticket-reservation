package com.ticket.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ticket.dto.ParkTicketPageQueryDTO;
import com.ticket.entity.Announcement;
import com.ticket.entity.ParkTicket;
import com.ticket.exception.CustomException;
import com.ticket.mapper.ParkTicketMapper;
import com.ticket.result.PageResult;
import com.ticket.service.ParkTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkTicketServiceImpl implements ParkTicketService {
    @Autowired
    private ParkTicketMapper parkTicketMapper;

    public void add(ParkTicket parkTicket) {
        //设置门票在售
        parkTicket.setStatus(1);
        //设置创建时间
        parkTicket.setCreateTime(LocalDateTime.now());
        //设置更新时间
        parkTicket.setUpdateTime(LocalDateTime.now());

        parkTicketMapper.insert(parkTicket);

    }

    @Override
    public PageResult pageQuery(ParkTicketPageQueryDTO parkTicketPageQueryDTO) {
        //分页插件自动填充分页sql
        PageHelper.startPage(parkTicketPageQueryDTO.getPageNum(), parkTicketPageQueryDTO.getPageSize());
        Page<ParkTicket> page = parkTicketMapper.pageQuery(parkTicketPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public void startOrStop(Integer status, Long id) {
        ParkTicket parkTicket = new ParkTicket();
        parkTicket.setStatus(status);
        parkTicket.setUpdateTime(LocalDateTime.now());
        parkTicket.setId(id);
        try {
            int rowsUpdated = parkTicketMapper.updateStatusById(parkTicket); // 更新状态
            if (rowsUpdated == 0) {
                throw new CustomException("更新失败，可能是记录不存在或状态未修改");
            }
        } catch (Exception e) {
            throw new CustomException("更新门票状态时发生异常");
        }
    }

    @Override
    public void deleteById(Long id) {
        parkTicketMapper.deleteById(id);
    }

    @Override
    public void update(ParkTicket parkTicket) {
        parkTicketMapper.update(parkTicket);
    }

    public List<ParkTicket> getAllTickets() {
        return parkTicketMapper.selectAllTickets();
    }
}
