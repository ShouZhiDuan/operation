package com.nvxclouds.operation.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.api.dto.SyncCommanDTO;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.SpEmergentCommand;
import com.nvxclouds.operation.biz.dto.CommandsListDTO;
import com.nvxclouds.operation.biz.dto.ResearchMethodListDTO;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.mapper.SpEmergentCommandMapper;
import com.nvxclouds.operation.biz.service.SpEmergentCommandService;
import com.nvxclouds.operation.biz.vo.CommandsQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:21
 * @Description:
 */
@Service
public class SpEmergentCommandServiceImpl extends AbstractService<SpEmergentCommand> implements SpEmergentCommandService {
    @Autowired
    private SpEmergentCommandMapper spEmergentCommandMapper;

    @Override//同步应急指令
    public void syncCommand(SyncCommanDTO dto) {
          SpEmergentCommand spEmergentCommand = new SpEmergentCommand();
          BeanUtils.copyProperties(dto,spEmergentCommand);
          //1未完成 2已完成
          spEmergentCommand.setIsFinish(1);
          //当前同步时间
          spEmergentCommand.setSyncTime(new Timestamp(System.currentTimeMillis()));
          spEmergentCommandMapper.insert(spEmergentCommand);
    }

    @Override//分页查询指令列表
    public Object commands(CommandsQueryVO query) {
        PageHelper.startPage(query.getPage(),query.getPerPage());
        List<CommandsListDTO> listDTOS = spEmergentCommandMapper.commands(query);
        CommonPage<CommandsListDTO> listPage = CommonPage.restPage(listDTOS);
        return listPage;
    }

    @Override
    public void finish(Long id) {
         SpEmergentCommand command = spEmergentCommandMapper.selectByPrimaryKey(id);
         Optional.ofNullable(command).orElseThrow(() -> new ApiProviderException(ApiProviderExceptionEnum.DATA_NOT_EXIT));
         command.setIsFinish(2);//1未完成 2已完成
         command.setFinishTime(new Timestamp(System.currentTimeMillis()));
         spEmergentCommandMapper.updateByPrimaryKeySelective(command);
         //同时调用监管端完成服务
         //TO DO 、、、、、、(待完成)
    }
}
