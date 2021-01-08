package com.nvxclouds.operation.biz.domain.pgsql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/21 13:20
 * @Description:数据节点黑名单列表实体
 */
@Data
@Table(name = "black_name")
public class BlackName {
    @Id
    @Column(name = "id" )
    private Long   id;

    @Column(name = "black_name_type")
    private Integer blackNameType;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "cause")
    private String cause;

}
