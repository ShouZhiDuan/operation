package com.nvxclouds.operation.biz.feign.block_chain;

import com.nvxclouds.blockchain.api.feign.MSFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/24 17:10
 * @Description:监管组织-服务
 */
//@FeignClient(value = "blockchain-sdk",url = "http://192.168.50.222:10811")
@FeignClient(value = "blockchain-sdk")
public interface MSFeignClient extends MSFeign {


}
