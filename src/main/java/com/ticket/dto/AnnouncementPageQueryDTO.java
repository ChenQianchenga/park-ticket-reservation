package com.ticket.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnnouncementPageQueryDTO implements Serializable {
    //公告状态
    private Integer status;
    //公告标题
    private String title;
    //页码
    private int pageNum;
    //每页显示记录数
    private int pageSize;
}
