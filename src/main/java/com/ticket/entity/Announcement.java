package com.ticket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private Long id; // 公告ID，唯一标识每条公告
    private String title; // 公告标题
    private String content; // 公告详细内容
    private String publisher; // 发布者，通常是管理员或发布公告的角色
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime; // 公告发布时间，默认为当前时间

    private LocalDateTime expiryTime; // 公告有效期，如果为空表示没有有效期

    private int status = 1; // 公告状态，默认启用

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
