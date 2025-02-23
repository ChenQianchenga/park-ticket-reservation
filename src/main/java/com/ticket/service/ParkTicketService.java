package com.ticket.service;

import com.ticket.dto.ParkTicketPageQueryDTO;
import com.ticket.entity.ParkTicket;
import com.ticket.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkTicketService {
    void add(ParkTicket parkTicket);

    PageResult pageQuery(ParkTicketPageQueryDTO parkTicketPageQueryDTO);

    void startOrStop(Integer status, Long id);

    void deleteById(Long id);

    void update(ParkTicket parkTicket);

    List<ParkTicket> getAllTickets();
}
