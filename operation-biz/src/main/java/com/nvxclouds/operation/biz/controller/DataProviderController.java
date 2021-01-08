package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.blockchain.api.feign.DataProviderFeign;
import com.nvxclouds.blockchain.api.query.*;
import com.nvxclouds.blockchain.api.vo.*;
import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.feign.block_chain.DataDemandFeignClient;
import com.nvxclouds.operation.biz.feign.block_chain.DataProviderFeignClient;
import com.nvxclouds.operation.biz.service.BlockChainQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:59
 * @Description:数据提供方区块链服务
 */

@RestController
@RequestMapping("/v1/dataProvider")
public class DataProviderController {

    @Autowired
    private BlockChainQueryService blockChainQueryService;

    @Autowired
    private DataProviderFeignClient dataProviderFeignClient;


    @Log(name = "数据提供方-查询数据节点注册信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dpQuery/queryDataNodeRegistration")
    public Object queryDataNodeRegistration(
            @RequestHeader("Authorization") String token,
            DataNodeRegistrationQuery query
    ) {
        BaseResult<DataNodeRegistrationVO> result = dataProviderFeignClient.queryDataNodeRegistration(query);
        return  result;
    }

    @Log(name = "数据提供方-查询数据节点修改申请信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dpQuery/queryDataNodeModificationApply")
    public Object queryDataNodeModificationApply(
            @RequestHeader("Authorization") String token,
            DataNodeModificationQuery query
    ) {
        BaseResult<DataNodeModificationVO> result = dataProviderFeignClient.queryDataNodeModification(query);
        return  result;
    }

    @Log(name = "数据提供方-查询增加数据集申请信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dpQuery/queryAddDatasetApply")
    public Object queryAddDatasetApply(
            @RequestHeader("Authorization") String token,
            DataSetIncreaseQuery query
    ) {
        BaseResult<DataSetIncreaseVO> result = dataProviderFeignClient.queryDataSetIncrease(query);
        return  result;
    }

    @Log(name = "数据提供方-查询开放数据集信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dpQuery/queryOpenDatasets")
    public Object queryOpenDatasets(
            @RequestHeader("Authorization") String token,
            DataSetOpenQuery query
    ) {
        BaseResult<DataSetOpenVO> result = dataProviderFeignClient.queryDataSetOpen(query);
        return  result;
    }

    @Log(name = "数据提供方-查询修改数据集信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dpQuery/queryModifyDatasets")
    public Object queryModifyDatasets(
            @RequestHeader("Authorization") String token,
            DataSetModificationQuery query
    ) {
        BaseResult<DataSetModificationVO> result = dataProviderFeignClient.queryDataSetModification(query);
        return  result;
    }

    @Log(name = "数据提供方-查询数据集下线信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dpQuery/queryDatasetsOffline")
    public Object queryDatasetsOffline(
            @RequestHeader("Authorization") String token,
            DataSetOfflineQuery query
    ) {
        BaseResult<DataSetOfflineVO> result = dataProviderFeignClient.queryDataSetOffline(query);
        return  result;
    }

    @Log(name = "数据提供方-查询查看研究结果信息", biz = LogTypeEnum.OPERATION)
    @GetMapping("/dpQuery/queryNodeDataResearchResults")
    public Object queryNodeDataResearchResults(
            @RequestHeader("Authorization") String token,
            StudyResultQuery query
    ) {
        BaseResult<StudyResultVO> result = dataProviderFeignClient.queryStudyResult(query);
        return  result;
    }


}
