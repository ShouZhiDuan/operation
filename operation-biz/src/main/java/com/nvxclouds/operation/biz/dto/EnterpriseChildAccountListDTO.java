package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 15:11
 * @Description: 企业子账号数据
 */
@Data
public class EnterpriseChildAccountListDTO {
    private String  id;
    private String  userName;
    private String  realName;
    private String  accountType;
    private String  department;
    private String  phone;
}
