package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/17 17:46
 * @Description: 数据集日、月统计
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetDayMonthDTO {
    private  String  dataSetName;
    private  String  groupTime;
    private  Long    count;
    private  String  organization;
}
