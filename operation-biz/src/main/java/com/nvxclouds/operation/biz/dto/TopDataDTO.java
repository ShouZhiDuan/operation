package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/9 9:56
 * @Description: 展示区块面板顶部数据
 */
@Data
public class TopDataDTO {

       private Long blockAmount;//区块数量

       private String tradeAmount;//交易数量

       private Integer peerNodeAmount;//节点数量

       private Integer chainAmount;//链码数量
}
