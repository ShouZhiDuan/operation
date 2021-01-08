package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.service.DataNodesService;
import com.nvxclouds.operation.biz.vo.medusa_vo.DataNodesBlackNameListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/21 10:48
 * @Description: 黑名单服务API
 */
@RestController
@RequestMapping("/v1/blackName")
public class BlackNameController {

     @Autowired
     private DataNodesService dataNodesService;

    @Log(name = "黑名单列表查询接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/list")
    public Object list(DataNodesBlackNameListQuery query) {
        /**
         * 1表示个人研究者黑名单
         * 2表示企业研究者黑名单
         * 3表示数据提供者黑名单
         */
        Object object = dataNodesService.blackNameList(query);
        return BaseResult.ok(object);
    }

    @Log(name = "黑名单详情查询接口", biz = LogTypeEnum.OPERATION)
    @GetMapping("/details/{type}/{id}")
    public Object details(@PathVariable("type") Integer type,@PathVariable("id") Integer id) {
        /**
         * 1表示个人研究者黑名单
         * 2表示企业研究者黑名单
         * 3表示数据提供者黑名单
         */
        Object object = dataNodesService.details(type,id);
        return BaseResult.ok(object);
    }


    @Log(name = "黑名单取消操作接口", biz = LogTypeEnum.OPERATION)
    @PostMapping("/cancle/{type}/{id}")
    public Object cancleBlackName(@PathVariable("type") Integer type,@PathVariable("id") Integer id) {
        /**
         * 1表示个人研究者黑名单
         * 2表示企业研究者黑名单
         * 3表示数据提供者黑名单
         */
        dataNodesService.cancleBlackName(type,id);
        return BaseResult.ok();
    }



}
