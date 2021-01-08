package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 15:04
 * @Description: 数据节点报表数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodeReporDTO {
    private String areaName;
    private List<DataNodeStatusDataDTO> list;
}
