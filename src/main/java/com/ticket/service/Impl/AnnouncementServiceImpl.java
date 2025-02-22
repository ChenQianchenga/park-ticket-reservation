package com.ticket.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ticket.dto.AnnouncementPageQueryDTO;
import com.ticket.entity.Announcement;
import com.ticket.exception.CustomException;
import com.ticket.mapper.AnnouncementMapper;
import com.ticket.result.PageResult;
import com.ticket.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;


    //分页查询公告
    public PageResult PageQuery(AnnouncementPageQueryDTO announcementDto) {
        //分页查询插件自动生成分页功能
        PageHelper.startPage(announcementDto.getPageNum(),announcementDto.getPageSize());
        Page<Announcement> page = announcementMapper.PageQuery(announcementDto);
        //返回封装PageResult对象
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    //新增公告
    public void add(Announcement announcement) {
        announcement.setPublisher("Admin");
        announcement.setCreateTime(LocalDateTime.now());
        announcement.setUpdateTime(LocalDateTime.now());
        announcement.setPublishTime(LocalDateTime.now());

        announcementMapper.add(announcement);

    }

    //公告的启用和禁用
    public void startOrStop(Integer status, Long id) {
        Announcement announcement = new Announcement();
        announcement.setStatus(status);
        announcement.setUpdateTime(LocalDateTime.now());
        announcement.setId(id);
        try {
            int rowsUpdated = announcementMapper.updateStatusById(announcement); // 更新状态
            if (rowsUpdated == 0) {
                throw new CustomException("更新失败，可能是记录不存在或状态未修改");
            }
        } catch (Exception e) {
            throw new CustomException("更新员工状态时发生异常");
        }

    }

    @Override
    public void deleteById(Long id) {
        announcementMapper.deleteById(id);
    }

    @Override
    public void update(Announcement announcement) {
        announcementMapper.update(announcement);
    }
}
