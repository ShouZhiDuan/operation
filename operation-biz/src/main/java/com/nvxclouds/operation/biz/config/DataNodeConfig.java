package com.nvxclouds.operation.biz.config;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 17:26
 * @Description:
 *  1未注册（NOT COLLECTED_）
 *  2未审批（INACTIVATED_）
 *  3在线（审批->ALIVE）
 *  4冻结（黑明单操作->BLOCKED ）
 *  5暂停（SUSPENDED）
 *  6离线（下线）：OFFLINE
 */
public class DataNodeConfig {


    public final static List<String> status = Arrays.asList(
            "NOT COLLECTED_",
            "INACTIVATED_",
            "ALIVE",
            "BLOCKED",
            "SUSPENDED",
            "OFFLINE"
    );



}
