package com.nvxclouds.operation.biz.controller;

import com.alibaba.fastjson.JSON;
import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.enums.DataNodeStatus;
import com.nvxclouds.operation.biz.service.DataNodesService;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataNodesQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 14:38
 * @Description:数据节点API
 *  1未注册（NOT COLLECTED_）
 *  2未审批（INACTIVATED_）
 *  3在线（审批->ALIVE）
 *  4冻结（黑明单操作->BLOCKED ）
 *  5暂停（SUSPENDED）
 *  6离线（下线）：OFFLINE
 */
@Slf4j
@RestController
@RequestMapping("/v1/dataNode")
public class DataNodesController {

    @Autowired
    private DataNodesService dataNodesService;

    @Log(name = "数据节点列表查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/list")
    //@Permission(name = "dataNode:list")
    public Object list(DataNodesQuery query) throws ParseException {
        return BaseResult.ok(dataNodesService.list(query));
    }

    @Log(name = "数据节点详情查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/{id}")
    //@Permission(name = "dataNode:details")
    public BaseResult<DataNodeDetailsDTO> details(@PathVariable(name = "id") Long id) {
        DataNodeDetailsDTO detailsDTO =  dataNodesService.details(id);
        return BaseResult.ok(detailsDTO);
    }

    /**
     * 废弃API
     */
    @Log(name = "数据节点暂停、恢复操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/suspended")
    public BaseResult suspended(@Valid DataNodesSuspendedDTO dto) throws IOException {
        dataNodesService.suspended(dto);
        return BaseResult.ok();
    }

    /**
     *  将数据节点加入黑名单，修改为下线状态，并同步medusa，该节点的数据集也同时下线。
     *  如有数据集正在参与研究，则研究也停止，并告知数据挖掘者（短信推送）。
     */
    @Log(name = "数据节点黑名单操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/blacked")
    public BaseResult<DataNodeDetailsDTO> blacked(@Valid @RequestBody DataNodesBlackedDTO dto) throws IOException {
        log.info("当前数据节点加入黑名单数据：" + JSON.toJSONString(dto));
        dataNodesService.blacked(dto);
        return BaseResult.ok();
    }

    @Log(name = "数据节点报表查询接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/repor")
    //@Permission(name = "dataNode:report:list")
    public BaseResult repor() {
        Object listRepor = dataNodesService.repor();
        return BaseResult.ok(listRepor);
    }


    @Log(name = "数据节点审批操作接口", biz = LogTypeEnum.OPERATION)
    @PostMapping("/approval")
    public BaseResult approval(@RequestBody DataNodeApprovalDTO dto) {
        dataNodesService.approval(dto.getDataNodeId());//数据节点审批操作
        return BaseResult.ok();
    }

    /**
     * <<暂停>>：暂停数据节点，该节点的数据集也同时暂停使用。
     * 如有数据集正在参与研究，则研究也停止，并告知数据挖掘者（短信推送）。
     * <<恢复>>：恢复数据节点为在线状态，该节点的数据集为可用状态。
     */
    @Log(name = "数据节点恢复、暂停操作接口", biz = LogTypeEnum.OPERATION)
    @PostMapping("/regain/suspended")
    public BaseResult regain(@Valid @RequestBody RegainSuspendedDTO dto) throws IOException {
        log.info("当前数据节点暂停、恢复操作数据：" + JSON.toJSONString(dto));
        //type = 1表示恢复、2表示暂停
        DataNodesSuspendedDTO dataNodesSuspendedDTO = new DataNodesSuspendedDTO();
        //设置数据节点
        dataNodesSuspendedDTO.setId(dto.getDataNodeId());
        //1表示恢复操作
        if(1 == dto.getType()){ dataNodesSuspendedDTO.setStatus(DataNodeStatus.SUSPENDED.getStatus()); }
        //2表示暂停操作
        if(2 == dto.getType()){ dataNodesSuspendedDTO.setStatus(DataNodeStatus.ALIVE.getStatus());}
        //dataNodesService.regain(dto.getDataNodeId(),dto.getType()); 已废弃
        dataNodesService.suspended(dataNodesSuspendedDTO);//新暂停、恢复逻辑
        return BaseResult.ok();
    }




































}
