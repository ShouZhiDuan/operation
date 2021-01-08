package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.SystemLog;
import com.nvxclouds.operation.biz.domain.licence.LicencePackage;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.vo.SysLogQuery;
import com.nvxclouds.operation.biz.vo.licence.LicenceOrderQueryVO;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/24 16:52
 * @Description:
 */
public interface LicenceService extends Service<LicencePackage> {

    /**
     * 新增编辑许可证信息
     * @param dto
     */
    void addLicence(LicencePackageAddDTO dto);

    /**
     * 查询许可证详情
     * @param id
     */
    Object findLicence(String id);

    /**
     * 查询许可证列表
     */
    Object listLicence(BaseQuery query);

    /**
     * 许可证上架操作
     * @param dto
     */
    void putOn(PutOnDTO dto);

    /**
     * 获取许可证订单列表
     * @param vo
     */
    Object orderList(LicenceOrderQueryVO vo);

    /**
     * 查询续费菜单
     * @param id
     * @return
     */
    Object costMenus(String id);

    /**
     * 许可证续费操作
     * @param dto
     */
    void renew(RenewDTO dto);
}
