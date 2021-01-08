package com.nvxclouds.operation.biz.domain.licence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 16:39
 * @Description: 许可证基本信息
 */
@Data
@Table(name = "licence_package")
@NoArgsConstructor
@AllArgsConstructor
public class LicencePackage {

    @Id
    @Column(name = "id")
    private String id;
    //许可证套餐名称
    @Column(name = "name")
    private String  name;
    // INDIVIDUAL：个人   ENTERPRISE：企业
    @Column(name = "type")
    private String  type;
    // 套餐价格 元/月
    @Column(name = "price")
    private BigDecimal price;
    //许可证套餐描述
    @Column(name = "description")
    private String  description;
    //创建时间
    @Column(name = "create_time")
    private Timestamp createTime;
    // 1表示未上架   2表示已上架
    @Column(name = "status")
    private Integer  status;
    // 上架时间
    @Column(name = "put_on_time")
    private Timestamp  putOnTime;
    //当前许可证支持的人数，个人默认支持1人；企业支持n人
    @Column(name = "number_of_people")
    private Integer  numberOfPeople;
}
