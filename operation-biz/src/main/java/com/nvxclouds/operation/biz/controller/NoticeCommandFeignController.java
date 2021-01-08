package com.nvxclouds.operation.biz.controller;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.operation.api.dto.SyncCommanDTO;
import com.nvxclouds.operation.api.dto.SyncNoticeDTO;
import com.nvxclouds.operation.api.feign.NoticeCommandFeigns;
import com.nvxclouds.operation.biz.service.SpEmergentCommandService;
import com.nvxclouds.operation.biz.service.SpEmergentNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:39
 * @Description:
 */
@RestController
@RequestMapping("/emergent")
public class NoticeCommandFeignController implements NoticeCommandFeigns {

      @Autowired
      private SpEmergentCommandService spEmergentCommandService;

      @Autowired
      private SpEmergentNoticeService spEmergentNoticeService;


    /**
     * 同步<<应急指令>>应急指令服务
     */
     @PostMapping("/syncCommand")
     public BaseResult syncCommand(@Valid @RequestBody SyncCommanDTO dto){
         spEmergentCommandService.syncCommand(dto);
         return BaseResult.ok();
     }

    /**
     * 同步<<紧急通知>></紧急通知>服务
     */
    @PostMapping("/syncNotice")
    public BaseResult syncNotice(@Valid @RequestBody SyncNoticeDTO dto){
        spEmergentNoticeService.syncNotice(dto);
        return BaseResult.ok();
    }



}
