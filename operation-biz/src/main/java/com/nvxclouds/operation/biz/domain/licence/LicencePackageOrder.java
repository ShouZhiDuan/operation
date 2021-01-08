package com.nvxclouds.operation.biz.domain.licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 17:03
 * @Description:许可证订单信息
 */
@Data
@Table(name = "licence_package_order")
@NoArgsConstructor
@AllArgsConstructor
public class LicencePackageOrder {

    @Id
    @Column(name = "id" )
    private  String   id;

    //当前订单购买了哪个套餐，对应licence_package中的主键ID
    @Column(name = "licence_package_id" )
    private  String   licencePackageId;

    //续费总金额
    @Column(name = "amount_money" )
    private BigDecimal amountMoney;

    //续费几个月
    @Column(name = "amount_month" )
    private  Integer   amountMonth;

    //购买时间
    @Column(name = "created" )
    private  Timestamp   created;

    //1已支付 2未支付 3已激活 4已到期
    @Column(name = "status" )
    private  Integer   status;

    //激活时间
    @Column(name = "actived_time" )
    private  Timestamp   activedTime;

    //过期时间
    @Column(name = "expire_time" )
    private Timestamp expireTime;

    //用户ID(谁购买的)
    @Column(name = "user_id" )
    private Long userId;
}
