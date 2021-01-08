package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.annotation.Permission;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.service.SpEmergentCommandService;
import com.nvxclouds.operation.biz.service.SpEmergentNoticeService;
import com.nvxclouds.operation.biz.vo.CommandsQueryVO;
import com.nvxclouds.operation.biz.vo.NoticeQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 14:05
 * @Description: 应急指令、应急通知相关API
 */
@RestController
@RequestMapping("/v1/emergent")
public class NoticeCommandController {

      @Autowired
      private SpEmergentNoticeService spEmergentNoticeService;

      @Autowired
      private SpEmergentCommandService spEmergentCommandService;

      @Log(name = "指令分页查询列表", biz = LogTypeEnum.OPERATION)
      @GetMapping("/commands")
      //@Permission(name = "emergencyOrder:list")
      public Object commands(CommandsQueryVO query) {
        return BaseResult.ok(spEmergentCommandService.commands(query));
      }

      @Log(name = "完成指令操作", biz = LogTypeEnum.OPERATION)
      @PostMapping("/command/finish/{id}")
      //@Permission(name = "emergencyOrder:add")
      public Object finish(@PathVariable("id") Long id) {
        spEmergentCommandService.finish(id);
        return BaseResult.ok();
      }

      @Log(name = "紧急通知分页查询列表", biz = LogTypeEnum.OPERATION)
      @GetMapping("/notices")
      //@Permission(name = "emergencyNotice:list")
      public Object notices(NoticeQueryVO query) {
            return BaseResult.ok(spEmergentNoticeService.notices(query));
      }

      @Log(name = "完成紧急通知操作", biz = LogTypeEnum.OPERATION)
      @PostMapping("/notices/finish/{id}")
      //@Permission(name = "emergencyNotice:add")
      public Object noticesFinish(@PathVariable("id") Long id) {
            spEmergentNoticeService.noticesFinish(id);
            return BaseResult.ok();
      }

      @Log(name = "紧急通知详情接口", biz = LogTypeEnum.OPERATION)
      @GetMapping("/notices/{id}")
      //@Permission(name = "emergencyNotice:detail")
      public Object details(@PathVariable("id") Long id) {
            return BaseResult.ok(spEmergentNoticeService.details(id));
      }

}
