package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 13:47
 * @Description:许可证列表数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicencePackageListDTO {

    //许可证套餐名称
    private String  name;

    //当前编辑的许可证ID
    private String id;
    // INDIVIDUAL：个人   ENTERPRISE：企业
    private String type;
    //上架时间
    private String putOnTime;
    // 套餐价格 元/月
    private BigDecimal price;
    //状态 上架 未上架
    private Integer  status;
    //许可证套餐描述
    private String  description;
    //许可证下包含的方法 格式逗号隔开：a,b,c,d......
    private String methodNames;
}
