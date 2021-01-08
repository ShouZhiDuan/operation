package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 14:44
 * @Description: 紧急通知数据
 */
@Data
public class NoticeListDTO {
    private Long     id;
    private String   title;
    private String   content;
    private String   publishTime;
    //紧急级别:1特级、2紧急、3一般
    private Integer  level;
    //发送方式：1内部通知、2短信通知、3邮件通知、4全部
    private String   sendType;
}
