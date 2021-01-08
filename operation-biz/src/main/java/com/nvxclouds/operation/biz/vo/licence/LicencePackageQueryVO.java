package com.nvxclouds.operation.biz.vo.licence;

import com.nvxclouds.operation.biz.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 14:08
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicencePackageQueryVO extends BaseQuery {
    // 1表示未上架   2表示已上架
    private Integer status;
    // INDIVIDUAL：个人   ENTERPRISE：企业
    private String type;
}
