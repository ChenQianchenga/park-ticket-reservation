package com.ticket.controller.user;

import com.ticket.dto.AnnouncementPageQueryDTO;
import com.ticket.result.PageResult;
import com.ticket.result.R;
import com.ticket.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("userAnnouncementController")
@RequestMapping("/user/announcement")
@Slf4j
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    /*
    查询当前所有生效中的公告信息，按照生成时间排序
     */
    @GetMapping("/page")
    public R<PageResult> PageQuery(AnnouncementPageQueryDTO announcementDto) {
        log.info("查询公告：{}",announcementDto);
        announcementDto.setStatus(1);
        PageResult pageResult = announcementService.PageQuery(announcementDto);
        return R.success(pageResult);
    }
}
