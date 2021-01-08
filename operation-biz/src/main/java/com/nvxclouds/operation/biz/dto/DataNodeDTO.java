package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 15:04
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodeDTO {

    private String dataNodeId;
    private String nodeName;
    private String organization;
    private String createdTime;
    private String area;
    private String url;
    private String status;

}
