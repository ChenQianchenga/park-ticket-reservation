package com.ticket.mapper;

import com.github.pagehelper.Page;
import com.ticket.dto.TicketReservationPageQueryDTO;
import com.ticket.entity.TicketReservation;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface TicketReservationMapper {

    Integer countByDate(LocalDate visitDate);

    void save(TicketReservation ticketReservation);

    Page<TicketReservation> PageQuery(TicketReservationPageQueryDTO ticketReservationPageQueryDTO);

    void deleteById(Integer reservationId);

    void update(TicketReservation ticketReservation);
}
