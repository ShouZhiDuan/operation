package com.nvxclouds.operation.biz.feign.block_chain;

import com.nvxclouds.blockchain.api.feign.FabricFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/8 17:32
 * @Description:
 */
@FeignClient(value = "blockchain-sdk")
public interface FabricFeignClient extends FabricFeign {

}
