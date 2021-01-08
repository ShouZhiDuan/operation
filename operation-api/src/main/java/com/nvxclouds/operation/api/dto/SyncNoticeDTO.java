package com.nvxclouds.operation.api.dto;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 13:39
 * @Description:
 */
@Data
public class SyncNoticeDTO {
    //标题
    private String title;

    //通知内容
    private String content;

    //发布时间
    private Timestamp publishTime;

    //紧急级别:1特级、2紧急、3一般
    private Integer level;

    //发送方式：1内部通知、2短信通知、3邮件通知、4全部
    private Integer sendType;

    //监管同步到运营的时间
    private Timestamp syncTime;
}
