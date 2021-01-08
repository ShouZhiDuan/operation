package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 14:38
 * @Description:操作许可证上下架
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutOnDTO {
    @NotNull(message = "许可证ID不能为空")
    private String id;
    // 1表示未上架   2表示已上架
    @NotNull(message = "许可证status不能为空")
    private Integer  status;
}
