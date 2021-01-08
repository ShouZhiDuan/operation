package com.nvxclouds.operation.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.nvxclouds.blockchain.api.dto.BlockChainBaseInfoDTO;
import com.nvxclouds.blockchain.api.query.BaseQuery;
import com.nvxclouds.blockchain.api.support.CommonPage;
import com.nvxclouds.common.base.pojo.BaseResult;
import com.nvxclouds.common.core.annotation.Log;
import com.nvxclouds.common.core.enums.LogTypeEnum;
import com.nvxclouds.operation.biz.feign.block_chain.FabricFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotBlank;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/8 17:44
 * @Description:
 */

@RestController
@RequestMapping("/v1/fabric")
public class FabricController {

    @Autowired
    private FabricFeignClient fabricFeignClient;


    /**
     * 查询平台-区块数量
     */
//    @Log(name = "区块链-查询区块数量", biz = LogTypeEnum.OPERATION)
//    @GetMapping("/queryBlockChainAmount")
//    public BaseResult queryBlockChainAmount() {
//        Long amount = fabricFeignClient.queryBlockChainAmount();
//        return  BaseResult.ok(amount);
//    }

    /**
     * 查询平台-历史交易条数
     */
//    @Log(name = "区块链-查询历史交易数量", biz = LogTypeEnum.OPERATION)
//    @GetMapping("/queryTradeAmount")
//    public BaseResult queryTradeAmount() {
//        String amount = fabricFeignClient.queryTradeAmount();
//        return  BaseResult.ok(amount);
//    }

    /**
     * 查询平台-链码数量
     */
//    @Log(name = "区块链-查询链码数量", biz = LogTypeEnum.OPERATION)
//    @GetMapping("/queryChainCodeAmount")
//    public BaseResult queryChainCodeAmount() {
//        Integer amount = fabricFeignClient.queryChainCodeAmount();
//        return  BaseResult.ok(amount);
//    }

    /**
     * 查询平台-节点数量
     */
//    @Log(name = "区块链-查询节点数量", biz = LogTypeEnum.OPERATION)
//    @GetMapping("/queryPeerAmount")
//    public BaseResult queryPeerAmount() {
//        Integer amount = fabricFeignClient.queryPeerAmount();
//        return  BaseResult.ok(amount);
//    }

    /**
     * 查询平台-节点名称列表
     */
//    @Log(name = "区块链-查询节点名称列表", biz = LogTypeEnum.OPERATION)
//    @GetMapping("/qureyPeerNameList")
//    public BaseResult qureyPeerNameList() {
//        List<String> list = fabricFeignClient.qureyPeerNameList();
//        return  BaseResult.ok(list);
//    }

    /**
     * 查询平台-分页查询区块列表
     */
    @Log(name = "区块链-分页查询区块列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryBlockList")
    public BaseResult queryBlockList(BaseQuery query) {
        CommonPage page = fabricFeignClient.queryBlockList(query);
        return  BaseResult.ok(page);
    }

    /**
     * 查询平台-区块详情
     */
    @Log(name = "区块链-查询区块详情", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryBlockByBlockNumber")
    public BaseResult queryBlockByBlockNumber(Long blockNumber) {
        BlockChainBaseInfoDTO dto = fabricFeignClient.queryBlockByBlockNumber(blockNumber);
        return  BaseResult.ok(dto);
    }

    /**
     * 查询平台-分页查询当前区块下的所有交易列表
     */
    @Log(name = "区块链-分页查询当前区块下的所有交易列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryTransactionListByBlockNumber")
    public BaseResult queryTransactionListByBlockNumber(BaseQuery query) {
        CommonPage psge = fabricFeignClient.queryTransactionListByBlockNumber(query);
        return  BaseResult.ok(psge);
    }

    /**
     * 查询平台-分页查询区块全部交易列表
     */
    @Log(name = "区块链-分页查询区块全部交易列表", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryTransactionList")
    public BaseResult queryTransactionList(BaseQuery query) {
        CommonPage psge = fabricFeignClient.queryTransactionList(query);
        return  BaseResult.ok(psge);
    }

    /**
     * 查询平台-根据transactionId查询交易详情
     */
    @Log(name = "区块链-根据transactionId查询交易详情", biz = LogTypeEnum.OPERATION)
    @GetMapping("/queryTransactionDetails")
    public BaseResult queryTransactionDetails(@RequestParam @NotBlank(message = "transactionId不能为空") String transactionId) {
        Object details = fabricFeignClient.queryTransactionDetails(transactionId);
        return  BaseResult.ok(details);
    }




}
