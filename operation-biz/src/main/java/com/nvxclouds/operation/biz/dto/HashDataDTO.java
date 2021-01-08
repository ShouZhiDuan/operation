package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/24 16:08
 * @Description:区块hash数据
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HashDataDTO {
     //通道名称
     private String channelName;
     //hash值
     private String hashValue;
     //hash内容
     private String hashContents;
}
