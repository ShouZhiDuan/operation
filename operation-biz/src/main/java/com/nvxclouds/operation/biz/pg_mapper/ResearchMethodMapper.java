package com.nvxclouds.operation.biz.pg_mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.domain.pgsql.ResearchMethod;
import com.nvxclouds.operation.biz.dto.DataSetUsedListDTO;
import com.nvxclouds.operation.biz.dto.ReaserchMethodListDTO;
import com.nvxclouds.operation.biz.dto.ResearchMethodDataListDTO;
import com.nvxclouds.operation.biz.dto.ResearchMethodListDTO;
import com.nvxclouds.operation.biz.vo.medusa_vo.ResearchMethodQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/22 14:11
 * @Description: 研究方法操作
 */
@Repository
public interface ResearchMethodMapper extends Mapper<ResearchMethod> {

    /**
     * 分页查询研究方法列表
     */
    List<ResearchMethodListDTO> methods(@Param("query") ResearchMethodQuery query);

    /**
     * 查询研究方法适用数据列表
     */
    List<ResearchMethodDataListDTO> applyList(@Param("nameDB") String nameDB);

    /**
     * 数据使用情况详情列表分页查询
     */
    List<DataSetUsedListDTO> setMethods(@Param("id") Long id);

    /**
     * 根据方法类型查找适用的研究方法
     */
    List<ReaserchMethodListDTO> getMethodListByType(@Param("type") Integer type);
}
