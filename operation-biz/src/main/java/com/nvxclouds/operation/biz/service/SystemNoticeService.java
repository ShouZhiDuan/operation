package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.domain.SystemNotice;
import com.nvxclouds.operation.biz.vo.SysNoticeQuery;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:41
 * @Description:
 */
public interface SystemNoticeService extends Service<SystemNotice> {

    /**
     * 发布系统通知
     * @param token
     * @param notice
     * @return
     */
    Object addNotice(String token, SystemNotice notice);


    /**
     * 查询系统通知详情
     * @param id
     * @return
     */
    SystemNotice noticeDetails(Long id);


    /**
     * 分页查询系统通知列表
     * @return
     */
    Object noticeList(SysNoticeQuery query);



}
