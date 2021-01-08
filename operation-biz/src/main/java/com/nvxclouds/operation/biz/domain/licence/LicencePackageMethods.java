package com.nvxclouds.operation.biz.domain.licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 16:55
 * @Description: 许可证与研究方法绑定数据
 */
@Data
@Table(name = "licence_package_methods")
@NoArgsConstructor
@AllArgsConstructor
public class LicencePackageMethods {
    @Id
    @Column(name =  "id")
    private String id;
    //许可证ID
    @Column(name = "licence_package_id" )
    private String licencePackageId;
    //研究方法名称
    @Column(name = "method_name" )
    private String methodName;
}
