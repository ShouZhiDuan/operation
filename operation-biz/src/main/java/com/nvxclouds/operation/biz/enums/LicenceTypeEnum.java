package com.nvxclouds.operation.biz.enums;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/27 11:18
 * @Description:
 */
public enum LicenceTypeEnum {
    INDIVIDUAL("INDIVIDUAL"),//个人类型许可证
    ENTERPRISE("ENTERPRISE")//企业类型许可证
    ;

    private String type;

    LicenceTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
