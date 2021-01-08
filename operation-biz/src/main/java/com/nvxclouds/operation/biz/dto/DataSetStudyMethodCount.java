package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/20 15:04
 * @Description:研究方法数据集使用统计
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetStudyMethodCount {
    private String methodName;//方法名称
    private Long dataSetCount;//数据集数量
}
