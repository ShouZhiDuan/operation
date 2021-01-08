package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.pgsql.DataSets;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.service.DataNodesService;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataNodesQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataSetsQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.UsedCountQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 14:38
 * @Description:数据集API
 */

@RestController
@RequestMapping("/v1/dataSet")
public class DataSetsController {

    @Autowired
    private DataNodesService dataNodesService;

    @Log(name = "数据集列表查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/list")
    //@Permission(name = "dataSet:list")
    public Object list(DataSetsQuery query) {
        return BaseResult.ok(dataNodesService.setList(query));
    }

    @Log(name = "数据集状态报表查询接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/repor")
    //@Permission(name = "dataSet:report:list")
    public BaseResult repor() {
        Object listRepor = dataNodesService.reporSet();
        return BaseResult.ok(listRepor);
    }


    /**
     * 暂停：暂停数据集，修改状态为不可用，如该数据集正在参与研究，则研究也停止，并告知数据挖掘者（短信推送）。
     * 恢复：恢复数据集为可用状态。
     */
    @Log(name = "数据集恢复、暂停操作接口", biz = LogTypeEnum.OPERATION)
    @PostMapping("/regain/suspended")
    public BaseResult regain(@RequestBody RegainSuspendedDTO dto) throws IOException {
        //dataNodesService.regainSet(dto.getDataSetId(),dto.getType());//废弃
        dataNodesService.regainSetNew(dto);//新逻辑
        return BaseResult.ok();
    }


    @Log(name = "数据集使用(日/月)", biz = LogTypeEnum.OPERATION)
    @GetMapping("/day/month")
    //@Permission(name = "dataSet:statistics:list")
    public BaseResult dayMonth(@RequestParam("type") Integer type, BaseQuery query) {
        //日月统计数据集使用列表 type=1日纬度2月纬度
        return BaseResult.ok(dataNodesService.dayMonth(type,query));
    }


    @Log(name = "统计研究方法下的数据集数量", biz = LogTypeEnum.OPERATION)
    @GetMapping("/methodDataSet/count")
    public BaseResult methodDataSetCount() {
        return BaseResult.ok(dataNodesService.methodDataSetCount());
    }


    @Log(name = "数据集历史使用统计列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/usedCount/list")
    //@Permission(name = "dataSet:usage:list")
    public BaseResult usedCountList(UsedCountQuery query) {
        Object page = dataNodesService.usedCountList(query);
        return BaseResult.ok(page);
    }



}
