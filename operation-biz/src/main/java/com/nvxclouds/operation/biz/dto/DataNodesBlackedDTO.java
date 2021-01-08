package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 16:12
 * @Description: 数据节点黑名单操作
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodesBlackedDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "操作状态不能为空")
    private String status;
    @NotBlank(message = "加入黑名单原因不能为空")
    private String cause;
}
