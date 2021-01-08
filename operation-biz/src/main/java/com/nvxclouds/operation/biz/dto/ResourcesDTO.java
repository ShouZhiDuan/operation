package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 15:04
 * @Description: 系统资源数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourcesDTO {
    private String nodeName;
    private String ip;
    private String net;//网络
    private String cpuUsage;//cpu使用百分比
    private String memUsage;//内存使用百分比
    private String type;//类型，目前逻辑上不支持先置空
}
