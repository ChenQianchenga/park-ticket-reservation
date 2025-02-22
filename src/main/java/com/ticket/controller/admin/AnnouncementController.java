package com.ticket.controller.admin;

import com.ticket.dto.AnnouncementPageQueryDTO;
import com.ticket.entity.Announcement;
import com.ticket.result.PageResult;
import com.ticket.result.R;
import com.ticket.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/announcement")
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
        PageResult pageResult = announcementService.PageQuery(announcementDto);
        return R.success(pageResult);
    }
    //新增公告
    @PostMapping
    public R<String> add(@RequestBody Announcement announcement) {
        log.info("新增公告：{}", announcement);
        announcementService.add(announcement);
        return R.success();
    }
    //公告状态切换
    @PostMapping("status/{status}")
    public R<String> startOrStop(@PathVariable Integer status, @RequestParam Long id) {
        log.info("启用、禁用公告状态：{}，{}", status, id);

        announcementService.startOrStop(status, id);
        return R.success("状态更新成功");
    }

    @DeleteMapping
    public R<String> deleteById(Long id) {
        log.info("删除分类:{}", id);
        announcementService.deleteById(id);
        return R.success("公告删除成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Announcement announcement) {
        log.info("修改公告:{}", announcement);
        announcementService.update(announcement);
        return R.success("公告修改成功");
    }
}
