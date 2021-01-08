package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 16:36
 * @Description:许可证新增数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicencePackageAddDTO {
    //当前编辑的许可证ID
    private String id;
    //许可证套餐名称
    @NotBlank(message = "许可证名称不能为空")
    private String  name;
    // INDIVIDUAL：个人   ENTERPRISE：企业
    @NotNull(message = "请选择许可证类型")
    private String type;
    //许可证下包含的方法 格式逗号隔开：a,b,c,d......
    @NotBlank(message = "请选择研究方法")
    private String methodNames;
    // 套餐价格 元/月
    @NotNull(message = "请输入价格")
    private BigDecimal price;
    //许可证套餐描述
    @NotBlank(message = "请输入描述")
    private String  description;
    //当前许可证支持的人数，个人默认支持1人；企业支持n人
    private Integer  numberOfPeople;

}
