package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/16 16:17
 * @Description:数据节点报表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodeAreaDTO {
    private String areaName;
    private String alive = "0";//在线
    private String notCollected = "0";//未注册
    private String offline = "0";//下线
}
