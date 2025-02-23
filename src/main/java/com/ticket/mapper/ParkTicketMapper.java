package com.ticket.mapper;

import com.github.pagehelper.Page;
import com.ticket.dto.ParkTicketPageQueryDTO;
import com.ticket.entity.ParkTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkTicketMapper {
    void insert(ParkTicket parkTicket);

    Page<ParkTicket> pageQuery(ParkTicketPageQueryDTO parkTicketPageQueryDTO);

    int updateStatusById(ParkTicket parkTicket);

    void deleteById(Long id);

    void update(ParkTicket parkTicket);

    List<ParkTicket> selectAllTickets();

    ParkTicket getByType(String ticketType);
}
