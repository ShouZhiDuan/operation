package com.nvxclouds.operation.biz.enums;

/**
 * @Author：ShouZhi@Duan
 */

public enum DataNodeStatus {
  /**
   * 未注册
   */
  NOT_COLLECTED("NOT COLLECTED"),
  /**
   * 已注册，但是长时间未响应
   */
  OFFLINE("OFFLINE"),
  /**
   * 未审批
   */
  INACTIVATED("INACTIVATED"),
  /**
   * 被加入黑名单
   */
  BLOCKED("BLOCKED"),
  /**
   * 被暂停服务
   */
  SUSPENDED("SUSPENDED"),
  /**
   * 已注册，正常连接状态
   */
  ALIVE("ALIVE")
  ;


  private String status;

  DataNodeStatus(String status) {
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
