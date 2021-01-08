package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.dto.CancleBlackDTO;
import com.nvxclouds.operation.biz.service.DataNodesService;
import com.nvxclouds.operation.biz.service.ResearcherBlacklistService;
import com.nvxclouds.operation.biz.vo.licence.IndividualBlackListQueryVO;
import com.nvxclouds.operation.biz.vo.licence.IndividualListQueryVO;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataNodeBlackNameQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 13:11
 * @Description: 黑名单相关服务
 */
@RestController
@RequestMapping("/v1/blacklist")
public class ResearcherBlacklistController {

    @Autowired
    private ResearcherBlacklistService researcherBlacklistService;

    @Autowired
    private DataNodesService dataNodesService;


    @Log(name = "个人研究者黑名单列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryIndividualResearchersBlackList")
    //@Permission(name = "individualResearcherBlacklist:list")
    public Object queryIndividualResearchersBlackList(IndividualBlackListQueryVO query) {
        return BaseResult.ok(researcherBlacklistService.queryIndividualResearchersBlackList(query));
    }

    @Log(name = "个人、企业研究者黑名单详情", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryIndividualResearchersBlackDetails/{id}/{type}")
    //@Permission(name = "researcherBlacklist:detail")
    public Object queryIndividualResearchersBlackDetails(@PathVariable("id") Long id, @PathVariable("type") Integer type) {
        //type 1个人 2企业
        return BaseResult.ok(researcherBlacklistService.queryIndividualResearchersBlackDetails(id,type));
    }

    @Log(name = "个人、企业研究者黑名单取消操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/cancelResearchersBlack")
    //@Permission(name = "individualResearcherBlacklist:delete")
    public Object cancelResearchersBlack(@RequestBody CancleBlackDTO cancleBlackDTO) throws IOException {
        researcherBlacklistService.cancelResearchersBlack(cancleBlackDTO);
        return BaseResult.ok();
    }


    @Log(name = "企业研究者黑名单列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryEnterpriseResearchersBlackList")
    //@Permission(name = "enterpriseResearcherBlacklist:list")
    public Object queryEnterpriseResearchersBlackList(IndividualBlackListQueryVO query) {
        return BaseResult.ok(researcherBlacklistService.queryEnterpriseResearchersBlackList(query));
    }


    @Log(name = "数据提供方(数据节点)黑名单列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryDataNodeBlackNameList")
    //@Permission(name = "dataProviderBlacklist:list")
    public Object queryDataNodeBlackNameList(DataNodeBlackNameQuery query) {
        return BaseResult.ok(dataNodesService.queryDataNodeBlackNameList(query));
    }


    @Log(name = "数据提供方(数据节点)黑名单详情", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryDataNodeBlackNameDetails/{id}")
    //@Permission(name = "dataProviderBlacklist:detail")
    public Object queryDataNodeBlackNameDetails(@PathVariable("id") Long id) {
        return BaseResult.ok(dataNodesService.queryDataNodeBlackNameDetails(id));
    }


    @Log(name = "数据提供方(数据节点)黑名单取消操作", biz = LogTypeEnum.OPERATION)
    @PostMapping("/cancelDataNodeBlack")
    //@Permission(name = "dataProviderBlacklist:delete")
    public Object cancelDataNodeBlack(@RequestBody CancleBlackDTO cancleBlackDTO) {
        dataNodesService.cancelDataNodeBlack(cancleBlackDTO);
        return BaseResult.ok();
    }




}
