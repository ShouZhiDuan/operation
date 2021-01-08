package com.nvxclouds.operation.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: zhengxing.hu
 * @Date: 2020/5/20 15:06
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ApiProviderExceptionEnum {
    ILLEGAL_NOTICE_TYPE(5001,"非法系统通知类型"),
    PASS_TOKEN_ERRO(5002,"非法token"),
    NOT_EXIT_NOTICE(5003,"当前系统消息已被删除"),
    ILLEGAL_NODE_STATUS(5003,"非法数据节点状态"),
    NOT_EXIT_DATANODE(5003,"当前数据节点不存在"),
    NOT_EXIT_DATASET(5003,"当前数据集不存在"),
    ILLEGAL_OPERRATION(5003,"非法操作"),
    TYPE_NOT_NULL(5003,"查询类型type不能为空"),
    DATA_NOT_EXIT(5003,"当前数据不存在"),
    DATA_EXIT(5003,"当前数据数据已存在请重新填写"),
    ID_EXIT(5003,"当前ID重复"),
    STATUS_EX(5003,"当前用户数据异常"),
    REPETITION_PAY(5003,"请不要重复支付"),
    IS_BLACKED(5003,"当前数据已被加入黑名单"),
    IS_NOT_EXSIT(5003,"当前数据挖掘者不存在")
    ;
    private final int code; //状态码
    private final String msg; //状态码消息
}
