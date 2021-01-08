package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/16 17:30
 * @Description: 数据集状态数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetAreaDTO {
    private String areaName;
    private String usable = "0";//可用
    private String  disabled = "0";//不可用
}
