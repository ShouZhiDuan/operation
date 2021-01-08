package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.dto.DataMapDTO;
import com.nvxclouds.operation.biz.service.DataNodesService;
import com.nvxclouds.operation.biz.service.MedusaLicenseUsersService;
import com.nvxclouds.operation.biz.vo.DataSortQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/31 10:48
 * @Description: 系统机构相关API
 */
@RestController
@RequestMapping("/v1/structure")
public class SystemStructureController {

    @Autowired
    private DataNodesService dataNodesService;

    @Autowired
    private MedusaLicenseUsersService licenseService;

    @Log(name = "正在运行的研究列表查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryRunningStudies")
    public Object queryRunningStudies(BaseQuery query) {
        return BaseResult.ok(dataNodesService.queryRunningStudies(query));
    }



    @Log(name = "数据提供方排名查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/topXDataProvider")
    public Object topXDataProvider(DataSortQuery query) {
        return BaseResult.ok(dataNodesService.topXDataProvider(query));
    }



    @Log(name = "数据需求方排名查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/topXDataDemand")
    public Object topXDataDemand(DataSortQuery query) {
        return BaseResult.ok(licenseService.topXDataDemand(query));
    }



    @Log(name = "分布图数据查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dataMap")
    public Object dataMap() {
        List<DataMapDTO> list = licenseService.dataMap();
        return BaseResult.ok(list);
    }




}
