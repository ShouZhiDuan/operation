package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 14:23
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandsListDTO {
    private Long      id;
    //指令类型 1数据源 2计算服务器 3数据挖掘 4黑名单
    private Integer   type;
    private String    description;
    private String    publishTime;
    private String    responseTime;
    //1已响应 2未响应
    private Integer   status;
    //1未完成 2已完成
    private Integer   isFinish;
}
