package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/17 16:36
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodeApprovalDTO {
    @NotNull(message = "dataNodeId不能为空")
    private Long dataNodeId;
}
