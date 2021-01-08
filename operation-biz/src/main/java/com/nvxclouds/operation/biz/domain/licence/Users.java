package com.nvxclouds.operation.biz.domain.licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 11:01
 * @Description: License用户数据(个人研究者、企业研究者)
 */
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Column(name = "id")
    private Long id;
    //创建时间
    @Column(name = "created")
    private Timestamp created;
    //更新时间
    @Column(name = "updated")
    private Timestamp updated;
    //验证码
    @Column(name = "code")
    private String code;
    //邮箱
    @Column(name = "email")
    private String email;
    //
    @Column(name = "end_point_medusa")
    private String endPointMedusa;
    //
    @Column(name = "end_point_skadi")
    private String endPointSkadi;
    //姓
    @Column(name = "frist_name")
    private String fristName;
    //
    @Column(name = "institution")
    private String institution;
    //名
    @Column(name = "last_name")
    private String lastName;
    //证书ID
    @Column(name = "license_id")
    private String licenseId;
    //密码
    @Column(name = "password")
    private String password;
    //联系电话
    @Column(name = "phone")
    private String phone;
    //
    @Column(name = "phone_or_email")
    private Boolean phoneOrEmail;
    //同步状态
    @Column(name = "synced")
    private Boolean synced;
    //用户名
    @Column(name = "username")
    private String username;
    //
    @Column(name = "verified")
    private Boolean verified;
    //证件号码：身份证、营业执照
    @Column(name = "id_code")
    private String idCode;
    //联系地址
    @Column(name = "address")
    private String address;
    //公司、企业名称
    @Column(name = "company_name")
    private String companyName;
    //地区
    @Column(name = "area")
    private String area;
    //状态
    @Column(name = "status")
    private String status;
    //子账户上级ID
    @Column(name = "parent_id")
    private Long parentId;
    //子账户类型
    @Column(name = "enterprise_user_type")
    private String accountType;
    //部门
    @Column(name = "department")
    private String department;
    //账户类型：INDIVIDUAL、ENTERPRISE
    @Column(name = "user_type")
    private String userType;
}
