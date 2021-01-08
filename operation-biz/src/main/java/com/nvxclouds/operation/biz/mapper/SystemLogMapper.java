package com.nvxclouds.operation.biz.mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.domain.SystemLog;
import com.nvxclouds.operation.biz.dto.SyslogDTO;
import com.nvxclouds.operation.biz.vo.SysLogQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:35
 * @Description:系统日志
 */
@Repository
public interface SystemLogMapper extends Mapper<SystemLog>{

    /**
     * 查询系统日志列表
     */
   List<SyslogDTO> getSysLogList(@Param("query") SysLogQuery query);


   








}
