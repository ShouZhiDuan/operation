package com.nvxclouds.operation.biz.mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.domain.DataNodesSort;
import com.nvxclouds.operation.biz.domain.ProviderDataSort;
import com.nvxclouds.operation.biz.vo.DataSortQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/10 16:55
 * @Description: 数据需求方操作
 */
@Repository
public interface ProviderDataSortMapper extends Mapper<ProviderDataSort> {

    /**
     * 查询数据需求排名数据
     */
   List<ProviderDataSort> getProviderDataSortList(@Param("query") DataSortQuery query);



}
