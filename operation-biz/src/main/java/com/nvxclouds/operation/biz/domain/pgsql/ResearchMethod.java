package com.nvxclouds.operation.biz.domain.pgsql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;


@Data
@Table(name = "research_method")
public class ResearchMethod {
    @Id
    @Column(name = "id" )
    private Long id;
    //数据库逻辑名称
    @Column(name = "name_db")
    private String nameDB;
    //英文名称
    @Column(name = "name")
    private String name;
    //中文名称
    @Column(name = "name_zh")
    private String nameZh;
    //英文描述
    @Column(name = "description")
    private String description;
    //中文描述
    @Column(name = "description_zh")
    private String descriptionZh;
    //f未激活 t已激活
    @Column(name = "blocked")
    private Boolean blocked;
    //1：个人研究者 2：企业研究者 3：全部
    @Column(name = "type")
    private Integer type;
    //创建时间
    @Column(name = "created_time")
    private Timestamp createdTime;
}
