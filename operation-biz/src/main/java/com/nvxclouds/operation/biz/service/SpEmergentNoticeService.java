package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.api.dto.SyncNoticeDTO;
import com.nvxclouds.operation.biz.domain.SpEmergentNotice;
import com.nvxclouds.operation.biz.vo.NoticeQueryVO;


/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/28 11:17
 * @Description: 紧急通知服务
 */
public interface SpEmergentNoticeService extends Service<SpEmergentNotice> {

    /**
     * 同步紧急通知
     */
    void syncNotice(SyncNoticeDTO dto);

    /**
     * 查询紧急通知分页列表
     * @param query
     * @return
     */
    Object notices(NoticeQueryVO query);

    /**
     * 完成紧急通知操作
     * @param id
     */
    void noticesFinish(Long id);

    /**
     * 紧急通知详情查询
     * @param id
     */
    Object details(Long id);
}
