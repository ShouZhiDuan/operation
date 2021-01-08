package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/31 13:27
 * @Description: 分布图数据
 */
@Data
public class DataMapDTO {
     //地区名称
     private String area;
     //数据提供方数量
     private Long dataProviderCount = 0l;
     //数据需求方数量
     private Long dataDemandCount = 0l;
}
