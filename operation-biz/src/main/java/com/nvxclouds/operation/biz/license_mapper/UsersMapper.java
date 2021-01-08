package com.nvxclouds.operation.biz.license_mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.licence.Users;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseChildAccountQueryVO;
import com.nvxclouds.operation.biz.vo.licence.EnterpriseListQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 11:27
 * @Description:
 */
@Repository
public interface UsersMapper extends Mapper<Users> {

    //查询个人研究者数据列表
    List<IndividualResearchListDTO> queryIndividualResearchers(@Param("query") BaseQuery query);

    //查询企业研究者数据列表
    List<EnterpriseResearchListDTO> queryEnterpriseResearchers(@Param("query") EnterpriseListQueryVO query);

    //查询研究者子账号列表
    List<EnterpriseChildAccountListDTO > queryEnterpriseChildAccounts(@Param("query") EnterpriseChildAccountQueryVO query);

    //查询数据需求方排名列表
    List<TopDTO> topXDataDemand(@Param("topNum") Integer topNum);

    //查询分布图数据
    List<DataMapDTO> dataMap();
}
