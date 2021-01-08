package com.nvxclouds.operation.biz.mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.domain.SpEmergentNotice;
import com.nvxclouds.operation.biz.dto.NoticeListDTO;
import com.nvxclouds.operation.biz.vo.NoticeQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:12
 * @Description:紧急通知数据库操作
 */
@Repository
public interface SpEmergentNoticeMapper extends Mapper<SpEmergentNotice> {
    //查询紧急通知列表数据
    List<NoticeListDTO> notices(@Param("query") NoticeQueryVO query);
}
