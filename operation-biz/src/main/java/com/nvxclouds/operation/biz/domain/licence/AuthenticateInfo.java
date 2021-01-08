package com.nvxclouds.operation.biz.domain.licence;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/29 10:00
 * @Description:
 */
@Data
@Table(name = "authenticate_info")
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateInfo {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "id_card_front_url")
    private String idCardFrontUrl;//身份证正面

    @Column(name = "id_card_back_url")
    private String idCardBackUrl;//身份证反面

    @Column(name = "license_url")
    private String licenseUrl;//营业执照

    @Column(name = "users_id")
    private Long usersId;//所属用户ID

}
