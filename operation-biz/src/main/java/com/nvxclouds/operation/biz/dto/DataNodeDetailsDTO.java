package com.nvxclouds.operation.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/3 16:01
 * @Description: 数据节点详情数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataNodeDetailsDTO implements Serializable {
    //数据节点ID
    private Long dataNodeId;
    //数据节点名称
    private String nodeName;
    //机构名称
    private String organizationName;
    //组织代码
    private String organizationCode;
    //联系人
    private String contactPerson;
    //联系电话
    private String contactPhone;
    //联系地址
    private String contactAdress;
    //营业执照URL
    private String businessLicenseUrl;
    //状态：1未注册（NOT COLLECTED_）、2未审批（INACTIVATED_）、3在线（审批->ALIVE）、4冻结（黑明单操作-BLOCKED ）、5暂停（SUSPENDED）、6离线（下线）：OFFLINE
    private String status;
}
