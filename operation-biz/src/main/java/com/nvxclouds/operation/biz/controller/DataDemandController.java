package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.blockchain.api.query.*;
import com.nvxclouds.blockchain.api.vo.*;
import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.feign.block_chain.DataDemandFeignClient;
import com.nvxclouds.operation.biz.service.BlockChainQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:59
 * @Description:数据使用方
 */

@RestController
@RequestMapping("/v1/dataUsed")
public class DataDemandController {

    @Autowired
    private BlockChainQueryService blockChainQueryService;

    @Autowired
    private DataDemandFeignClient dataDemandFeignClient;


    @Log(name = "数据使用方-查询创建研究项目信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/duQuery/queryResearchProjects")
    public Object queryResearchProjects(
            @RequestHeader("Authorization") String token,
            StudyProjectQuery query
    ) {
        //创建研究项目查询
        BaseResult<StudyProjectVO> result =  dataDemandFeignClient.queryStudyProject(query);
        return  result;
    }


    @Log(name = "数据使用方-查询使用数据集申请信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/duQuery/queryUseDatasetApply")
    public Object queryUseDatasetApply(
            @RequestHeader("Authorization") String token,
            DataSetUsageApprovalQuery query
    ) {
        //查询使用数据集申请信息
        BaseResult<DataSetUsageApprovalVO> result =  dataDemandFeignClient.queryDataSetApproval(query);
        return  result;
    }


    @Log(name = "数据使用方-查询查看研究结果信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/duQuery/queryUserResearchResults")
    public Object queryUserResearchResults(
            @RequestHeader("Authorization") String token,
            StudyProjectResultQuery query
    ) {
        //查询查看研究结果信息
        BaseResult<StudyProjectResultVO> result = dataDemandFeignClient.queryStudyProjectApproval(query);
        return  result;
    }


    @Log(name = "数据使用方-查询购买授权信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/duQuery/queryPurchaseAuthorization")
    public Object queryPurchaseAuthorization(
            @RequestHeader("Authorization") String token,
            LicensePurchaseQuery query
    ) {
        //查询购买授权信息
        BaseResult<LicensePurchaseVO> result =  dataDemandFeignClient.queryLicensePurchase(query);
        return  result;
    }


    @Log(name = "数据使用方-查询激活授权信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/duQuery/queryActivateAuthorization")
    public Object queryActivateAuthorization(
            @RequestHeader("Authorization") String token,
            LicenseActivationQuery query
    ) {
        //查询购买激活信息
        BaseResult<LicenseActivationVO> result =  dataDemandFeignClient.queryLicenseActivation(query);
        return  result;
    }



}
