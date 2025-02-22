package com.ticket.mapper;

import com.github.pagehelper.Page;
import com.ticket.dto.AnnouncementPageQueryDTO;
import com.ticket.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper {

    Page<Announcement> PageQuery(AnnouncementPageQueryDTO announcementDto);

    void add(Announcement announcement);

    int updateStatusById(Announcement announcement);

    void deleteById(Long id);

    void update(Announcement announcement);
}
