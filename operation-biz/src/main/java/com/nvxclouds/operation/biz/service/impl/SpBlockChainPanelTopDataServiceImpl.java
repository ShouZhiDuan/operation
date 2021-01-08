package com.nvxclouds.operation.biz.service.impl;

import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.config.OrganizationPeerConfig;
import com.nvxclouds.operation.biz.domain.SpBlockChainPanelTopData;
import com.nvxclouds.operation.biz.dto.HashDataDTO;
import com.nvxclouds.operation.biz.dto.OrganizationTradeDTO;
import com.nvxclouds.operation.biz.dto.TopDataDTO;
import com.nvxclouds.operation.biz.feign.block_chain.FabricFeignClient;
import com.nvxclouds.operation.biz.service.SpBlockChainPanelTopDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:42
 * @Description:区块面板top数据服务
 */
@Service
public class SpBlockChainPanelTopDataServiceImpl extends AbstractService<SpBlockChainPanelTopData> implements SpBlockChainPanelTopDataService {

    @Autowired
    private FabricFeignClient fabricFeignClient;


    /**
     * 查询区块面板-top数据
     * @return
     */
    @Override
    public TopDataDTO topData() {
        TopDataDTO data = new TopDataDTO();
        //1、区块数量
        Long blockAmount = fabricFeignClient.queryBlockChainAmount();
        //2、交易数量
        String tradeAmount = fabricFeignClient.queryTradeAmount();
        //3、节点数量
        Integer peerNodeAmount = fabricFeignClient.queryPeerAmount();
        //4、链码数量
        Integer chainAmount = fabricFeignClient.queryChainCodeAmount();
        data.setBlockAmount(blockAmount);
        data.setTradeAmount(tradeAmount);
        data.setPeerNodeAmount(peerNodeAmount);
        data.setChainAmount(chainAmount);
        return data;
    }

    /**
     * 查询区块面板-节点名称列表
     * @return
     */
    @Override
    public List<String> nodeNames() {
         List<String> list = fabricFeignClient.qureyPeerNameList();
         return CollectionUtils.isEmpty(list) ? new ArrayList<>() : list;
    }

    /**
     * 查询区块面板-交易饼图数据
     * @return
     */
    @Override
    public Object pieChartData() {
        List resultList = fabricFeignClient.queryTradeCountGroupByOrganization();
        return resultList;
    }


    /**
     * 查询区块面板-hash详情
     * @return
     */
    @Override
    public List<HashDataDTO> hashDatas() {
        List<HashDataDTO> hashDataDTOS = new ArrayList<>();
        hashDataDTOS.add(HashDataDTO.builder().channelName("通道A").hashValue("d6drfwe76dw87wf6trw8ftw368ft63").hashContents("876dtw7df348twfgef768wefwf").build());
        hashDataDTOS.add(HashDataDTO.builder().channelName("通道B").hashValue("s876erf8yte87gfer78fyererge556").hashContents("sefytwe68f63tgyus67feg7665").build());
        hashDataDTOS.add(HashDataDTO.builder().channelName("通道C").hashValue("sd7h8e6gtrh78fgt658we8fdtyw78w").hashContents("sd77835gyuft673gyf63789rw6").build());
        return hashDataDTOS;
    }
}
