package com.nvxclouds.operation.biz.mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.domain.DataNodesSort;
import com.nvxclouds.operation.biz.vo.DataSortQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/10 16:55
 * @Description: 数据提供方排名操作
 */
@Repository
public interface DataNodesSortMapper  extends Mapper<DataNodesSort> {

    /**
     * 查询数据提供方排名数据
     */
   List<DataNodesSort> getDataNodeSortList(@Param("query") DataSortQuery query);



}
