package com.nvxclouds.operation.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 13:10
 * @Description: 应急指令同步数据
 */
@Data
public class SyncCommanDTO {

    //指令类型 1数据源 2计算服务器 3数据挖掘 4黑名单
    @NotNull
    private Integer type;

    //指令说明
    @NotBlank
    private String description;

    //发布时间
    @NotNull
    private Timestamp publishTime;

    //响应时间
    @NotNull
    private Timestamp responseTime;

    //1已响应 2未响应
    @NotNull
    private Integer status;

}
