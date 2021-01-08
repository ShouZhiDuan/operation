package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/21 16:55
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetUsedDTO {
    private  Long id;//数据集ID
    private  String dataSetName;
    private  String organization;
    private  String filename;
    private  String area;
    private  String createdTime;
    private  Long usedCount;
}
