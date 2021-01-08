package com.nvxclouds.operation.biz.vo.licence;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 15:06
 * @Description:许可证订单列表数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenceOrderQueryVO extends BaseQuery {
    //INDIVIDUAL：个人   ENTERPRISE：企业
    private String type;
    //1已支付 2未支付 3已激活 4已到期
    private Integer status;
}
