package com.nvxclouds.operation.biz.license_mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.licence.LicencePackage;
import com.nvxclouds.operation.biz.dto.LicenceOrderDTO;
import com.nvxclouds.operation.biz.dto.LicencePackageListDTO;
import com.nvxclouds.operation.biz.vo.licence.LicenceOrderQueryVO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 16:50
 * @Description: 许可证
 */
@Repository
public interface LicencePackageMapper extends Mapper<LicencePackage> {
    /**
     * 查询许可证列表
     * @param query
     */
    List<LicencePackageListDTO> listLicence(@Param("query") BaseQuery query);

    /**
     * 查询许可证订单列表
     * @param query
     */
    List<LicenceOrderDTO> orderList(@Param("query") LicenceOrderQueryVO query);


}
