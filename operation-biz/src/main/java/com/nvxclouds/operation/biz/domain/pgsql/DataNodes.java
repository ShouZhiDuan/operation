package com.nvxclouds.operation.biz.domain.pgsql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 17:48
 * @Description:数据节点
 */

@Data
@Table(name = "datanodes")
public class DataNodes {
     @Id
     @Column(name = "id" )
     private Long     id;
     private Timestamp created;
     private Timestamp  updated;
     private String  description;
     @Column(name = "last_upload_date")
     private Timestamp  lastUploadDate;
     private String  name;
     private String  status;
     private Boolean  synced;
     private String  url;
     private String  username;
     private String  area;
}
