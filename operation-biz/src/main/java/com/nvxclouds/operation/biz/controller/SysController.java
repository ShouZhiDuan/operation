package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.SystemNotice;
import com.nvxclouds.operation.biz.domain.pgsql.DataNodes;
import com.nvxclouds.operation.biz.dto.DataNodeDTO;
import com.nvxclouds.operation.biz.dto.SyslogDTO;
import com.nvxclouds.operation.biz.pg_mapper.DataNodesMapper;
import com.nvxclouds.operation.biz.service.SystemLogService;
import com.nvxclouds.operation.biz.service.SystemNoticeService;
import com.nvxclouds.operation.biz.vo.SysLogQuery;
import com.nvxclouds.operation.biz.vo.SysNoticeQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataNodesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 13:46
 * @Description:系统服务
 */

@RestController
@RequestMapping("/v1/sys")
public class SysController {

    @Autowired
    private SystemLogService systemLogService;

    @Autowired
    private DataNodesMapper dataNodesMapper;

    @Autowired
    private SystemNoticeService systemNoticeService;


    @Log(name = "系统-日志列表查询", biz = LogTypeEnum.OPERATION)
    @GetMapping("/getSysLogList")
    //@Permission(name = "system:log")
    public Object getSysLogList(@RequestHeader("Authorization") String token, SysLogQuery query) {
        CommonPage<SyslogDTO> page = systemLogService.getSysLogList(token, query);
        return BaseResult.ok(page);
    }



    @Log(name = "系统-系统通知发布", biz = LogTypeEnum.OPERATION)
    @PostMapping("/addNotice")
    //@Permission(name = "sys:addNotice")
    public Object addNotice(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody SystemNotice notice
    ) {
        systemNoticeService.addNotice(token,notice);
        return BaseResult.ok();
    }




    @Log(name = "系统-系统通知详情", biz = LogTypeEnum.OPERATION)
    @GetMapping("/noticeDetails/{id}")
    //@Permission(name = "sys:noticeDetails")
    public Object noticeDetails(@PathVariable(name = "id") Long id) {
        return BaseResult.ok(systemNoticeService.noticeDetails(id));
    }




    @Log(name = "系统-分页查询系统通知列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/noticeList")
    //@Permission(name = "sys:noticeList")
    public Object noticeList(SysNoticeQuery query) {
        return BaseResult.ok(systemNoticeService.noticeList(query));
    }



































    /**
     * =========================================================================================================
     * 以下内容用于测试
     * =========================================================================================================
     */
    @GetMapping("/getDataNodeList")
    public Object getDataNodeList() {
        //查询
        DataNodes nodes = new DataNodes();
        nodes.setId(66l);
        dataNodesMapper.select(nodes);
        //新增
        DataNodes nodesQuery = new DataNodes();
        nodes.setCreated(new Timestamp(System.currentTimeMillis()));
        nodes.setDescription("测试描述");
        nodes.setId(66l);
        nodes.setLastUploadDate(new Timestamp(System.currentTimeMillis()));
        nodes.setName("节点名称");
        nodes.setUpdated(new Timestamp(System.currentTimeMillis()));
        nodes.setStatus("status");
        nodes.setSynced(false);
        nodes.setUrl("www.baidu.com");
        nodes.setUsername("username");
        dataNodesMapper.insert(nodes);
        //xml查询
        List<DataNodeDTO> xmlList = dataNodesMapper.getDtataNodeList(null);
        return  xmlList;
    }



}
