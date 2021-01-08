package com.nvxclouds.operation.biz.enums;

/**
 * @Author：ShouZhi@Duan
 */

public enum DataSetStatus {
    /**
     * 已删除
     */
    DELETED("DELETED"),

    /**
     * 已注册，但是长时间未响应
     */
    OFFLINE("OFFLINE"),

    /**
     * 已注册，可以使用
     */
    ALIVE("ALIVE"),

    /**
     * 被暂停服务
     */
    SUSPENDED("SUSPENDED"),

    /**
     * 黑名单状态
     */
    BLOCKED ("BLOCKED")
    ;

    private String status;

    DataSetStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
