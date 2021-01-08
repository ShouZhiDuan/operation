package com.nvxclouds.operation.biz.dto.license_dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/6 10:34
 * @Description: 研究者黑名单取消操作数据
 */
@Data
public class CancelBlackNameDTO {
    @NotNull(message = "研究者ID不能为空")
    private Long id;//研究者id
}
