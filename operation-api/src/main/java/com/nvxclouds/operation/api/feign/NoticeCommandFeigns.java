package com.nvxclouds.operation.api.feign;

import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.operation.api.dto.SyncCommanDTO;
import com.nvxclouds.operation.api.dto.SyncNoticeDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:37
 * @Description: 应急响应：指令、通知服务
 */
public interface NoticeCommandFeigns {
    /**
     * 同步<<应急指令>>应急指令服务
     */
     @PostMapping("/emergent/syncCommand")
     BaseResult syncCommand(@Valid @RequestBody SyncCommanDTO dto);


    /**
     * 同步<<紧急通知>></紧急通知>服务
     */
     @PostMapping("/emergent/syncNotice")
     BaseResult syncNotice(@Valid @RequestBody SyncNoticeDTO dto);

}
