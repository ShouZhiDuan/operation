package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/24 16:08
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationTradeDTO {
     //组织名称
     private String organizationName;
     //当前组织交易数量
     private long tradeCount;
}
