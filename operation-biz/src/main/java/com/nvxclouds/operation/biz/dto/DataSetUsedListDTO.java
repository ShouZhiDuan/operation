package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 19:14
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetUsedListDTO {
    private  String  methodName;
    private  String  joinResearchTime;
    private  String  resrearchName;
    private  String  researchOrganization;
    private  String  researchArea;
}
