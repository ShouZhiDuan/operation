package com.nvxclouds.operation.biz.license_mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.licence.ResearcherBlacklist;
import com.nvxclouds.operation.biz.dto.IndividualResearcherBlackListDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 12:00
 * @Description:
 */
@Repository
public interface ResearcherBlacklistMapper extends Mapper<ResearcherBlacklist> {
    //查询个人研究者黑名单数据列表
    List<IndividualResearcherBlackListDTO> queryIndividualResearchersBlackList(@Param("query") BaseQuery query);

    //查询个人研究者黑名单数据列表
    List<IndividualResearcherBlackListDTO> queryEnterpriseResearchersBlackList(@Param("query") BaseQuery query);
}
