package com.nvxclouds.operation.biz.domain.licence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/19 16:10
 * @Description: 许可证激活码
 */
@Data
@Table(name = "activation_code")
@NoArgsConstructor
@AllArgsConstructor
public class ActivationCode {

    @Id
    @Column(name = "id" )
    private  Long   id;

    @Column(name = "code" )
    private String code;

}
