package com.nvxclouds.operation.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.SystemLog;
import com.nvxclouds.operation.biz.dto.SyslogDTO;
import com.nvxclouds.operation.biz.mapper.SystemLogMapper;
import com.nvxclouds.operation.biz.service.SystemLogService;
import com.nvxclouds.operation.biz.vo.SysLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 13:07
 * @Description:
 */
@Service
public class SystemLogServiceImpl extends AbstractService<SystemLog> implements SystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public CommonPage<SyslogDTO> getSysLogList(String token, SysLogQuery query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<SyslogDTO> pageList = systemLogMapper.getSysLogList(query);
        CommonPage<SyslogDTO> data =   CommonPage.restPage(pageList);
        return data;
    }


















}
