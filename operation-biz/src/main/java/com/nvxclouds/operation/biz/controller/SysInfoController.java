package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.service.DataNodesService;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataSetsQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.ResourcesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 14:38
 * @Description:系统模块API
 */

@RestController
@RequestMapping("/v1/sys")
public class SysInfoController {

    @Autowired
    private DataNodesService dataNodesService;

    @Log(name = "study-server系统资源列表查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/resources")
    public Object resources(ResourcesQuery query) {
        return BaseResult.ok(dataNodesService.resources(query));
    }








}
