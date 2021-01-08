package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.licence.ResearcherBlacklist;
import com.nvxclouds.operation.biz.dto.CancleBlackDTO;
import com.nvxclouds.operation.biz.vo.licence.IndividualBlackListQueryVO;

import java.io.IOException;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 13:12
 * @Description: 黑名单服务
 */
public interface ResearcherBlacklistService extends Service<ResearcherBlacklist> {

    //个人研究者黑名单列表查询
    Object queryIndividualResearchersBlackList(BaseQuery query);

    //个人研究者黑名单详情
    Object queryIndividualResearchersBlackDetails(Long id, Integer type);

    //个人、企业黑名单取消操作
    void cancelResearchersBlack(CancleBlackDTO cancleBlackDTO) throws IOException;

    //企业研究者黑名单列表查询
    Object queryEnterpriseResearchersBlackList(IndividualBlackListQueryVO query);
}
