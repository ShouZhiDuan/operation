package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.api.dto.SyncCommanDTO;
import com.nvxclouds.operation.biz.domain.SpEmergentCommand;
import com.nvxclouds.operation.biz.vo.CommandsQueryVO;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:20
 * @Description: 应急指令服务
 */
public interface SpEmergentCommandService extends Service<SpEmergentCommand> {

    /**
     * 应急指令同步
     */
    void syncCommand(SyncCommanDTO dto);

    /**
     * 指令分页查询
     * @param query
     */
    Object commands(CommandsQueryVO query);

    /**
     * 完成指令操作
     * @param id
     */
    void finish(Long id);
}
