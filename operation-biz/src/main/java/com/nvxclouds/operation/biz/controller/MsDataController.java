package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.blockchain.api.query.*;
import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.domain.SpBlockChainPanelTopData;
import com.nvxclouds.operation.biz.dto.HashDataDTO;
import com.nvxclouds.operation.biz.dto.OrganizationTradeDTO;
import com.nvxclouds.operation.biz.feign.block_chain.MSFeignClient;
import com.nvxclouds.operation.biz.service.BlockChainQueryService;
import com.nvxclouds.operation.biz.service.SpBlockChainPanelTopDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:59
 * @Description:监管方区块链数据查询
 */

@RestController
@RequestMapping("/v1/ms")
public class MsDataController {

    @Autowired
    private BlockChainQueryService blockChainQueryService;

    /**
     *
     * @param token 登录校验
     * @param query 查询对象
     * @return
     */
    @Log(name = "监管方-新数据集审批信息查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/msQuery/newDatasetApproval")
    public Object newDatasetApproval(@RequestHeader("Authorization") String token, DataSetApprovalQuery query) {
        return  blockChainQueryService.newDatasetApproval(token,query);
    }


    @Log(name = "监管方-数据集修改审批信息查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/msQuery/modifyDatasetApproval")
    public Object modifyDatasetApproval(@RequestHeader("Authorization") String token, DataSetApprovalModificationQuery query) {
        return  blockChainQueryService.modifyDatasetApproval(token,query);
    }


    @Log(name = "监管方-研究项目审批查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/msQuery/researchProjectApproval")
    public Object researchProjectApproval(@RequestHeader("Authorization") String token, StudyProjectApprovalQuery query) {
        return  blockChainQueryService.researchProjectApproval(token,query);
    }


    @Log(name = "监管方-使用数据集审批信息查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/msQuery/useDatasetApproval")
    public Object useDatasetApproval(@RequestHeader("Authorization") String token, DataSetUsageApprovalQuery query) {
        return  blockChainQueryService.useDatasetApproval(token,query);
    }


//    @Log(name = "监管方-新应用审批信息查询", biz = LogTypeEnum.OPERATION)
//    @GetMapping("/msQuery/newAppApproval")
//    public BaseResult<Object> newAppApproval(@RequestHeader("Authorization") String token, StudyProjectApprovalQuery query) {
//        //to do ......
//        return  BaseResult.ok();
//    }


//    @Log(name = "监管方-应用修改审批信息查询", biz = LogTypeEnum.OPERATION)
//    @GetMapping("/msQuery/modifyAppApproval")
//    public BaseResult<Object> modifyAppApproval(@RequestHeader("Authorization") String token, StudyProjectApprovalQuery query) {
//        //to do ......
//        return  BaseResult.ok();
//    }


    @Log(name = "监管方-查看研究结果信息查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/msQuery/queryProjectResearchResults")
    public Object queryProjectResearchResults(@RequestHeader("Authorization") String token, StudyProjectResultQuery query) {
        return  blockChainQueryService.queryProjectResearchResults(token,query);
    }








}
