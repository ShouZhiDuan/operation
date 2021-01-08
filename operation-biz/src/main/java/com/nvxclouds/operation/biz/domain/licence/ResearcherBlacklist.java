package com.nvxclouds.operation.biz.domain.licence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/30 11:57
 * @Description: 个人、企业研究者黑名单管理
 */
@Data
@Table(name = "researcher_blacklist")
public class ResearcherBlacklist {

    @Id
    @Column(name = "id")
    private  String id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "cause")
    private String cause;
}
