package com.nvxclouds.operation.biz.dto.medusa_dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/4 11:32
 * @Description: 数据节点暂停操作数据
 */
@Data
public class BlackNameSuspendedDTO {

    //1表示个人研究者黑名单 2表示企业研究者黑名单 3表示数据提供者黑名单
    @NotNull(message = "黑名单类型不能为空")
    private Integer blackNameType;

    //每一种block_name_type下对相应的资源数据主键ID
    @NotNull(message = "数据节点ID不能为空")
    private Long resourceId;

    //数据挖掘者手机号
    @NotNull(message = "数据挖掘者手机号不能为空")
    private String phone;
}
