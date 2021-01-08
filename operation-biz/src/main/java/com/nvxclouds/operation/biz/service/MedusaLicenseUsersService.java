package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.SpBlockTrade;
import com.nvxclouds.operation.biz.domain.licence.Users;
import com.nvxclouds.operation.biz.dto.AuthDTO;
import com.nvxclouds.operation.biz.dto.DataMapDTO;
import com.nvxclouds.operation.biz.dto.DoBlackDTO;
import com.nvxclouds.operation.biz.vo.DataSortQuery;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseChildAccountQueryVO;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseListQueryVO;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 11:29
 * @Description:
 */
public interface MedusaLicenseUsersService extends Service<Users> {

    /**
     * 个人研究者列表数据查询
     */
    Object queryIndividualResearchers(BaseQuery query);

    /**
     * 查询个人研究者认证信息
     * @param id
     * @return
     */
    Object queryIndividualResearchersAuthInfo(Long id, Integer type);

    /**
     * 个人研究者认证操作
     * @param authDTO
     */
    void individualResearchersAuth(AuthDTO authDTO);

    /**
     * 个人研究者、企业研究者黑名单操作
     * @param doBlackDTO
     */
    void individualEnterpriseResearchersBlack(DoBlackDTO doBlackDTO) throws IOException;

    /**
     * 企业研究者列表查询
     * @param query
     * @return
     */
    Object queryEnterpriseResearchers(EnterpriseListQueryVO query);

    /**
     * 企业研究者子账号列表查询
     * @param query
     * @return
     */
    Object queryEnterpriseChildAccounts(EnterpriseChildAccountQueryVO query);


    /**
     * 数据需求方排名查询
     * @param query
     * @return
     */
    Object topXDataDemand(DataSortQuery query);

    /**
     * 分布图数据查询
     * @return
     */
    List<DataMapDTO> dataMap();
}
