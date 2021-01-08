package com.nvxclouds.operation.biz.pg_mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.pgsql.DataNodes;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataNodesBlackNameListQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataNodesQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 17:55
 * @Description:数据节点
 */
@Repository
public interface DataNodesMapper extends Mapper<DataNodes> {

    /**查询数据节点列表*/
    List<DataNodeDTO> getDtataNodeList(@Param("query") DataNodesQuery query);


    /**
     * 查询数据节点报表
     */
    List<DataNodeReporDTO> repor();


    /**
     * 查询数据提供方黑名单列表(废弃)
     */
    List<DataProviderBlackNameDTO> dataProviderList(@Param("query") DataNodesBlackNameListQuery query);


    /**
     * 查询数据提供方黑名单列表(新在用)
     * @param query
     * @return
     */
    List<DataNodeBlackNameListDTO>  queryDataNodeBlackNameList(@Param("query") BaseQuery query);


    /**
     * 查询正在运行的研究扩列表
     * @param query
     * @return
     */
    List<RunningStudyListDTO> queryRunningStudies(@Param("query") BaseQuery query);

    /**
     * 查询数据提供方排名列表
     * @param topNum
     */
    List<TopDTO> topXDataProvider(@Param("topNum") Integer topNum, @Param("area") String area);

    /**
     * 查询分布图数据
     * @return
     */
    List<DataMapDTO> dataMap();
}
