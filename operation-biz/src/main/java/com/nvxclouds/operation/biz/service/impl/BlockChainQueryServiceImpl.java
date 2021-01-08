package com.nvxclouds.operation.biz.service.impl;

import com.nvxclouds.blockchain.api.query.*;
import com.nvxclouds.blockchain.api.vo.*;
import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.operation.biz.feign.block_chain.DataDemandFeignClient;
import com.nvxclouds.operation.biz.feign.block_chain.MSFeignClient;
import com.nvxclouds.operation.biz.service.BlockChainQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/28 11:02
 * @Description:区块链数据查询服务
 */
@Service
public class BlockChainQueryServiceImpl implements BlockChainQueryService {

    @Autowired
    private MSFeignClient msFeignClient;//监管方服务


    @Autowired
    private DataDemandFeignClient dataDemandFeignClient;

    /**
     * 查询新数据集审批信息
     * @param token
     * @param query
     * @return
     */
    @Override
    public Object newDatasetApproval(String token, DataSetApprovalQuery query) {
        //dataNode1002
//        DataSetApprovalQuery query1 = new DataSetApprovalQuery();
//        query1.setDataNodeID("dataNode1002");
//        query1.setOffsets(1);
//        query1.setPageSize(1);
        BaseResult<DataSetApprovalVO> result =  msFeignClient.queryDataSetApproval(query);
        return result;
    }

    /**
     * 数据集修改审批信息查询
     * @param token
     * @param query
     * @return
     */
    @Override
    public Object modifyDatasetApproval(String token, DataSetApprovalModificationQuery query) {
        BaseResult<DataSetApprovalModificationVO> result = msFeignClient.queryDataSetApprovalModification(query);
        return  result;
    }


    /***
     * 查询研究项目审批信息
     * @param token
     * @param query
     * @return
     */
    @Override
    public Object researchProjectApproval(String token, StudyProjectApprovalQuery query) {
        BaseResult<StudyProjectApprovalVO> result =  msFeignClient.queryStudyProjectApproval(query);
        return result;
    }


    /***
     * 使用数据集审批查询
     * @param token
     * @param query
     * @return
     */
    @Override
    public Object useDatasetApproval(String token, DataSetUsageApprovalQuery query) {
        BaseResult<DataSetUsageApprovalMSVO> result = msFeignClient.queryDataSetApproval(query);
        return result;
    }


    /**
     * 查询研究结果信息
     * @param token
     * @param query
     * @return
     */
    @Override
    public Object queryProjectResearchResults(String token, StudyProjectResultQuery query) {
        BaseResult<StudyProjectResultMSVO> result = msFeignClient.queryStudyProjectApproval(query);
        return result;
    }


    /**
     * 查询创建研究项目信息
     * @param token
     * @param query
     * @return
     */
    @Override
    public Object queryStudyProject(String token, StudyProjectQuery query) {
        BaseResult<StudyProjectVO> result =  dataDemandFeignClient.queryStudyProject(query);
        return result;
    }


    /**
     * 查询使用数据集申请信息
     * @param token
     * @param query
     * @return
     */
    @Override
    public Object queryDataSetApproval(String token, DataSetUsageApprovalQuery query) {
        BaseResult<DataSetUsageApprovalVO> result =  dataDemandFeignClient.queryDataSetApproval(query);
        return result;
    }

















}
