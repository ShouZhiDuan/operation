package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 17:29
 * @Description: 研究方法适用数据列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResearchMethodDataListDTO {
    private  String nodeName;
    private  String setName;
    private  String filename;
    private  String createdTime;
    private  String status;
}
