package com.nvxclouds.operation.biz.feign.block_chain;

import com.nvxclouds.blockchain.api.feign.DataDemandFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 目前已有服务：数据使用方、数据提供方、监管方
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/24 17:17
 * @Description:数据使用方-服务
 */
//@FeignClient(value = "blockchain-sdk",url = "http://192.168.50.222:10811")
@FeignClient(value = "blockchain-sdk")
public interface DataDemandFeignClient extends DataDemandFeign {


}
