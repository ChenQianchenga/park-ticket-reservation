package com.ticket.service;

import com.ticket.dto.AnnouncementPageQueryDTO;
import com.ticket.entity.Announcement;
import com.ticket.result.PageResult;
import org.springframework.stereotype.Service;


@Service
public interface AnnouncementService {
    PageResult PageQuery(AnnouncementPageQueryDTO announcementDto);

    void add(Announcement announcement);

    void startOrStop(Integer status, Long id);

    void deleteById(Long id);

    void update(Announcement announcement);
}
