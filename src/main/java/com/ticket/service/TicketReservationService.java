package com.ticket.service;

import com.ticket.dto.TicketReservationPageQueryDTO;
import com.ticket.entity.TicketReservation;
import com.ticket.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface TicketReservationService {
    void save(TicketReservation ticketReservation);

    PageResult PageQuery(TicketReservationPageQueryDTO ticketReservationPageQueryDTO);

    void deleteById(TicketReservation ticketReservation);

    void update(TicketReservation ticketReservation);
}
