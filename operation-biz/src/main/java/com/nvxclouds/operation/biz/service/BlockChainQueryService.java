package com.nvxclouds.operation.biz.service;

import com.nvxclouds.blockchain.api.query.*;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:26
 * @Description:运营平台区块链-数据查询服务
 */
public interface BlockChainQueryService {
    /**
     * ===============================================================================
     *                                  监管sdk角色
     * ===============================================================================
     */
    /**1、新数据集审批信息查询*/
    Object newDatasetApproval(String token, DataSetApprovalQuery query);

    /**2、数据集修改审批信息查询*/
    Object modifyDatasetApproval(String token, DataSetApprovalModificationQuery query);

    /**3、查询研究项目*/
    Object researchProjectApproval(String token, StudyProjectApprovalQuery query);

    /**4、使用数据集审批*/
    Object useDatasetApproval(String token, DataSetUsageApprovalQuery query);

    /**5、查询查看研究结果信息*/
    Object queryProjectResearchResults(String token, StudyProjectResultQuery query);




    /**
     * ===============================================================================
     *                                  数据使用方sdk角色
     * ===============================================================================
     */
    /**1、查询创建研究项目信息*/
    Object queryStudyProject(String token, StudyProjectQuery query);

    /**2、查询使用数据集申请信息*/
    Object queryDataSetApproval(String token, DataSetUsageApprovalQuery query);












}
