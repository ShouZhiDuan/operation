package com.nvxclouds.operation.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: zhengxing.hu
 * @Date: 2020/5/25 11:28
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum MSExceptionEnum {
    DATA_SET_APPLICATION_NOT_EXIST(4001, "数据审批详情不存在"),
    DATA_SET_NOT_EXIST(4002, "数据集不存在"),
    APPLY_DATA_SET_APPLICATION_FAILED(4003, "数据集审批失败"),
    DATA_SET_APPROVAL_REQUEST_FAILED(4004, "数据集审批失败"),
    STUDY_PROJECT_NOT_EXIST(4005, "研究项目不存在"),
    APPLY_STUDY_PROJECT_FAILED(4006, "研究项目审批失败"),
    DATA_NODE_NOT_EXIST(4007,"数据集节点不存在" );
    private final int code;
    private final String msg;
}
