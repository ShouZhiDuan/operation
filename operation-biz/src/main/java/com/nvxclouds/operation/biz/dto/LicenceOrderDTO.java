package com.nvxclouds.operation.biz.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 15:19
 * @Description:
 */
@Data
public class LicenceOrderDTO {
    //许可证订单ID
    private String    id;
    //许可证ID
    private String licencePackageId;
    //许可证名称
    private String  name;
    // INDIVIDUAL：个人   ENTERPRISE：企业
    private String  type;
    //续费总金额
    private BigDecimal amountMoney;
    //续费总月数
    private String  amountMonth;
    //购买时间
    private String  createdTime;
    //状态 //1已支付 2未支付 3已激活 4已到期
    private String  status;
    //激活之间
    private String  activedTime;
    //到期时间
    private String  expireTime;
    //methodNames
    private String methodNames;
}
