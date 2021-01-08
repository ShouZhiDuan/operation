package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 16:37
 * @Description: 数据黑名单详情数据
 */
@Data
public class DataProviderBlackNameDetailsDTO {
    private String created;
    private String username;
    private String nodeName;
    private String companyName;
    private String realName;
    private String phone;
    private String email;
    private String area;
    private String address;
    private String cause;//黑名单原因
}
