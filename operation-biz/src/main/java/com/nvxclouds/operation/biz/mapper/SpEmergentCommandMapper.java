package com.nvxclouds.operation.biz.mapper;

import com.nvxclouds.common.core.mapper.Mapper;
import com.nvxclouds.operation.biz.domain.SpEmergentCommand;
import com.nvxclouds.operation.biz.dto.CommandsListDTO;
import com.nvxclouds.operation.biz.vo.CommandsQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:10
 * @Description:紧急指令数据库操作
 */
@Repository
public interface SpEmergentCommandMapper extends Mapper<SpEmergentCommand> {

    //分页查询指令列表数据
    List<CommandsListDTO> commands(@Param("query") CommandsQueryVO query);






}
