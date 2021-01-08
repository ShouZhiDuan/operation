package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.domain.SpBlockChainPanelTopData;
import com.nvxclouds.operation.biz.dto.HashDataDTO;
import com.nvxclouds.operation.biz.dto.OrganizationTradeDTO;
import com.nvxclouds.operation.biz.dto.TopDataDTO;
import com.nvxclouds.operation.biz.service.SpBlockChainPanelTopDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:59
 * @Description:  区块面板
 */

@RestController
@RequestMapping("/v1/panel")
public class BlockPanelController {


    @Autowired
    private SpBlockChainPanelTopDataService spBlockChainPanelTopDataService;


    @Log(name = "运营系统-区块面板top数据接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/topData")
    public BaseResult<TopDataDTO> topData() {
        return  BaseResult.ok(spBlockChainPanelTopDataService.topData());
    }

    @Log(name = "运营系统-区块面板节点名称接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/nodeNamesData")
    public BaseResult<List<String>> nodeNames() {
        return  BaseResult.ok(spBlockChainPanelTopDataService.nodeNames());
    }


    @Log(name = "运营系统-区块面板组织<<交易饼图>>数据查询接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/pieChartData")
    public BaseResult<Object> pieChartData() {
        return  BaseResult.ok(spBlockChainPanelTopDataService.pieChartData());
    }

    @Log(name = "运营系统-区块面板组织<<区块hash详细信息>>数据查询接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/hashDatas")
    public BaseResult<List<HashDataDTO>> hashDatas() {
        return  BaseResult.ok(spBlockChainPanelTopDataService.hashDatas());
    }


}
