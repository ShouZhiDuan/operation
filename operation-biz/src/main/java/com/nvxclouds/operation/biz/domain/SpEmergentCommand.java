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
 * @Description: 应急指令
 */
@Data
@Builder
@Table(name = "sp_emergent_command")
@NoArgsConstructor
@AllArgsConstructor
public class SpEmergentCommand {
    @Id
    @Column(name = "ID" )
    private Long id;

    //指令类型 1数据源 2计算服务器 3数据挖掘 4黑名单
    @Column(name = "type")
    private Integer type;

    //指令说明
    @Column(name = "description")
    private String description;

    //发布时间
    @Column(name = "publish_time")
    private Timestamp publishTime;

    //响应时间
    @Column(name = "response_time")
    private Timestamp responseTime;

    //1已响应 2未响应
    @Column(name = "status")
    private Integer status;

    //1未完成 2已完成
    @Column(name = "is_finish")
    private Integer isFinish;

    //当前指令完成时间
    @Column(name = "finish_time")
    private Timestamp finishTime;

    //监管同步到运营的时间
    @Column(name = "sync_time")
    private Timestamp syncTime;
}
