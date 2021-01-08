package com.nvxclouds.operation.biz.pg_mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.pgsql.DataSets;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataSetsQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.UsedCountQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 17:55
 * @Description:数据集
 */
@Repository
public interface DataSetsMapper extends Mapper<DataSets> {

    /**
     * 查询数据集列表
     */
    List<DataSetDTO> getDataSetsList(@Param("query") DataSetsQuery query);


    /**
     * 查询系统资源列表
     */
    List<ResourcesDTO> resources(@Param("nodeName") String nodeName);


    /**
     * 查询数据集
     */
    List<DataNodeReporDTO> repor();


    /**
     * 按日统计数据集
     */
    List<DataSetDayMonthDTO> byDayCount(@Param("query") BaseQuery query);


    /**
     * 按月统计数据集
     */
    List<DataSetDayMonthDTO> byMonthCount(@Param("query") BaseQuery query);

    /**
     * 按照研究方法统计数据集使用数据
     */
    List<DataSetStudyMethodCount> byStudyMethodCount();


    /**
     * 查询数据历史使用列表
     * @param query
     */
    List<DataSetUsedDTO> usedCountList(@Param("query") UsedCountQuery query);
}
