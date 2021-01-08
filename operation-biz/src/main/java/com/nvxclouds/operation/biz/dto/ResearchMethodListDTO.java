package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 16:35
 * @Description:研究方法列表数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResearchMethodListDTO {
    private  Long id;
    private  String name;
    private  String createdTime;
    private  Integer type;
    private  String description;
    private  Long dataNodeCount;
}
