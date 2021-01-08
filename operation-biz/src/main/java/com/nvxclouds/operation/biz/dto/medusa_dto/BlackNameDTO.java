package com.nvxclouds.operation.biz.dto.medusa_dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/4 11:32
 * @Description: 加入黑名单数据
 */
@Data
public class BlackNameDTO {

    //1表示个人研究者黑名单 2表示企业研究者黑名单 3表示数据提供者黑名单
    private Integer blackNameType;

    //每一种block_name_type下对相应的资源数据主键ID
    private Long resourceId;

    //加入黑名单的原因描述
    private String cause;

    //数据挖掘者手机号
    private String phone;
}
