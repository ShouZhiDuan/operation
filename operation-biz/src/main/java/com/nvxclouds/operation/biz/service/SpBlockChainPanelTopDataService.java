package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.domain.SpBlockChainPanelTopData;
import com.nvxclouds.operation.biz.dto.HashDataDTO;
import com.nvxclouds.operation.biz.dto.OrganizationTradeDTO;
import com.nvxclouds.operation.biz.dto.TopDataDTO;

import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:41
 * @Description:
 */
public interface SpBlockChainPanelTopDataService extends Service<SpBlockChainPanelTopData> {

    /**查询-区块面板top数据*/
    TopDataDTO topData();

    /**查询-区块面板节点名称列表*/
    List<String> nodeNames();

    /**查询-区块面板交易饼图数据列表*/
    Object pieChartData();

    /**查询-区块面板hash数据列表*/
    List<HashDataDTO> hashDatas();
}
