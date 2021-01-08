package com.nvxclouds.operation.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 10:58
 * @Description: 应急通知
 */
@Data
@Builder
@Table(name = "sp_emergent_notice")
@NoArgsConstructor
@AllArgsConstructor
public class SpEmergentNotice {
    @Id
    @Column(name = "ID" )
    private Long id;

    //标题
    @Column(name = "title")
    private String title;

    //通知内容
    @Column(name = "content")
    private String content;

    //发布时间
    @Column(name = "publish_time")
    private Timestamp publishTime;

    //紧急级别:1特级、2紧急、3一般
    @Column(name = "level")
    private Integer level;

    //发送方式：1内部通知、2短信通知、3邮件通知、4全部
    @Column(name = "send_type")
    private String sendType;

    //监管同步到运营的时间
    @Column(name = "sync_time")
    private Timestamp syncTime;

    //1未完成 2已完成
    @Column(name = "is_finish")
    private Integer isFinish;

    //当前通知完成时间
    @Column(name = "finish_time")
    private Timestamp finishTime;

}
