package com.nvxclouds.operation.biz.feign.block_chain;

import com.nvxclouds.blockchain.api.feign.DataProviderFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/24 17:19
 * @Description:数据提供方-服务
 */
//@FeignClient(value = "blockchain-sdk",url = "http://192.168.50.222:10811")
@FeignClient(value = "blockchain-sdk")
public interface DataProviderFeignClient extends DataProviderFeign {


}
