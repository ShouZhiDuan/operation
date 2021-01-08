package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 16:21
 * @Description: 数据节点黑名单列表数据
 */
@Data
public class DataNodeBlackNameListDTO {
    private Long   id;
    private String  nodeName;
    private String  organizationName;
    private String  area;
    private String  concatName;
    private String  userName;
    private String  createdTime;
}
