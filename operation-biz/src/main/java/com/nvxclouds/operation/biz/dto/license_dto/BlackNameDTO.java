package com.nvxclouds.operation.biz.dto.license_dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/5 10:48
 * @Description: 研究者黑名单操作数据、研究者取消黑名单数据
 */
@Data
public class BlackNameDTO {
    @NotNull(message = "研究者ID不能为空")
    private  Long id;
    //1、个人研究者 2、企业研究者
    @NotNull(message = "操作类型不能为空")
    private Integer type;
    //黑名单原因
    private String cause;
}
