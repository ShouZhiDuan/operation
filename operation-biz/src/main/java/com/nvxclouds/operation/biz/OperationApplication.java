package com.nvxclouds.operation.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/15 16:43
 * @Description:运营系统
 */


@EnableFeignClients
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@EnableDiscoveryClient
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.nvxclouds.*.biz", "com.nvxclouds.common.core"})
public class OperationApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperationApplication.class,args);
    }

}
