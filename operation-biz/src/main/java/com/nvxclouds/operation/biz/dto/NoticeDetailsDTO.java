package com.nvxclouds.operation.biz.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 15:08
 * @Description:
 */
@Data
public class NoticeDetailsDTO {
    private Long id;
    //标题
    private String title;
    //通知内容
    private String content;
    //发布时间
    private String publishTime;
    //紧急级别:1特级、2紧急、3一般
    private Integer level;
    //发送方式：1内部通知、2短信通知、3邮件通知、4全部
    private String sendType;
    //1未完成 2已完成
    private Integer isFinish;
}
