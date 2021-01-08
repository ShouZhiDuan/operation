package com.nvxclouds.operation.biz.mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.domain.SystemLog;
import com.nvxclouds.operation.biz.domain.SystemNotice;
import com.nvxclouds.operation.biz.dto.SyslogDTO;
import com.nvxclouds.operation.biz.vo.SysLogQuery;
import com.nvxclouds.operation.biz.vo.SysNoticeQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:35
 * @Description: 系统通知
 */
@Repository
public interface SystemNoticeMapper extends Mapper<SystemNotice>{

      List<SystemNotice>  getSysNoticeList(@Param("query") SysNoticeQuery query);

}
