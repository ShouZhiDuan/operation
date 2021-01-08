package com.nvxclouds.operation.biz.vo.licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 12:56
 * @Description:许可证详情数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicencePackageDetailsVO {
    //当前编辑的许可证ID
    private String id;
    //许可证套餐名称
    private String  name;
    // INDIVIDUAL：个人   ENTERPRISE：企业
    private String type;
    //许可证下包含的方法 格式逗号隔开：a,b,c,d......
    private String methodNames;
    // 套餐价格 元/月
    private BigDecimal price;
    //许可证套餐描述
    private String  description;
    //当前许可证支持的人数，个人默认支持1人；企业支持n人
    private Integer  numberOfPeople;
}
