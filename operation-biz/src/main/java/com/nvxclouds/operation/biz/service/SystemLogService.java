package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.SystemLog;
import com.nvxclouds.operation.biz.dto.SyslogDTO;
import com.nvxclouds.operation.biz.vo.SysLogQuery;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:41
 * @Description:
 */
public interface SystemLogService extends Service<SystemLog> {

    /**
     * 查询系统日志列表
     * @param token
     * @param query
     * @return
     */
    CommonPage<SyslogDTO> getSysLogList(String token, SysLogQuery query);



}
