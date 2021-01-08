package com.nvxclouds.operation.biz.enums;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 13:36
 * @Description: 研究者状态管理
 */
public enum LicenseUserStatusEnum {

    /**
     * 未验证
     */
    UNVERIFIED("UNVERIFIED"),
    /**
     * 已验证,未认证
     */
    VERIFIED_UNAUTHORIZED("VERIFIED_UNAUTHORIZED"),
    /**
     * 认证申请已提交
     */
    AUTHORIZATION_PENDING("AUTHORIZATION_PENDING"),
    /**
     * 未认证，文件已上传
     */
    UNAUTHORIZED_FILE_UPLOADED("UNAUTHORIZED_FILE_UPLOADED"),
    /**
     * 认证已通过
     */
    AUTHORIZED("AUTHORIZED"),
    /**
     * 认证失败
     */
    DENIED("DENIED"),
    /**
     * 暂停
     */
    SUSPENDED("SUSPENDED"),
    /**
     * 注销
     */
    TERMINATED("TERMINATED");

    private String status;

    LicenseUserStatusEnum(String status) {
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
