package com.nvxclouds.operation.biz.dto;

import lombok.Data;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 12:09
 * @Description: 企业研究者列表数据
 */
@Data
public class EnterpriseResearchListDTO {
    private Long  id;
    private String  userName;
    private String  companyName;
    private String  enterpriseType;
    private String  realName;
    private String  phone;
    private String  email;
    private String  address;
    private String  idCode;
    private String  status;
    private Boolean  isBlocked;//是否加入过黑名单
}
