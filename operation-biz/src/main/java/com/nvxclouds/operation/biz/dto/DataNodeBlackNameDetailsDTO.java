package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/21 13:15
 * @Description: 数据提供方黑名单详情
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodeBlackNameDetailsDTO {
     private String createdTime;
     private String userName;
     private String nodeName;
     private String organization;
     private String concatName;
     private String concatPhone;
     private String concatEmial;
     private String area;
     private String concatAddress;
     private String blackNameCause;//黑名单原因
}
