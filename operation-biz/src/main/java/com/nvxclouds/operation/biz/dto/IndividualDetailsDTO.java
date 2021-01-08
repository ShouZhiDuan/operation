package com.nvxclouds.operation.biz.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 14:12
 * @Description: 个人研究者黑名单详情数据
 */
@Data
public class IndividualDetailsDTO {

    //创建时间
    private Timestamp created;

    //邮箱
    private String email;


    //姓
    private String fristName;

    //名
    private String lastName;

    //联系电话
    private String phone;

    //用户名
    private String username;

    //姓名、联系人
    private String realName = (this.fristName + this.lastName);

    //个人表示：身份证号码 企业表示：机构统一代码
    private String idCode;

    //联系地址
    private String address;

    //公司、企业名称、所属单位
    private String companyName;

    //地区
    private String area;

    //加入黑名单原因
    private String cause;
}
