package com.nvxclouds.operation.biz.pg_mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.pgsql.BlackName;
import com.nvxclouds.operation.biz.domain.pgsql.DataSets;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataSetsQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/21
 * @Description:黑名单数据操作
 */
@Repository
public interface BlackNameMapper extends Mapper<BlackName> {

}
