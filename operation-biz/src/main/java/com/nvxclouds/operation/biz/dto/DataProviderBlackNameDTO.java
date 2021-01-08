package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/21 10:58
 * @Description: 数据提供方黑名单数据模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataProviderBlackNameDTO {
    private Long id;
    private String createdTime;
    private String nodeName;
    private String organization;
    private String area;
    private String conactName;
}
