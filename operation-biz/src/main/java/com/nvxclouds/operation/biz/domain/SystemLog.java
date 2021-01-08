package com.nvxclouds.operation.biz.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统日志数据
 */

@Data
@Table(name = "system_log")
public class SystemLog  implements Serializable {
    @Id
    @Column(name = "ID" )
    private Long id;
    @Column(name = "user_ID")
    private Long userId;
    @Column(name = "url")
    private String url;
    @Column(name = "method")
    private String method;
    @Column(name = "params")
    private String params;
    @Column(name = "remote_ip")
    private String remoteIp;
    @Column(name = "device")
    private String device;
    @Column(name = "time")
    private Long time;
    @Column(name = "result")
    private Integer result;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "key_word")
    private String keyWord;

}
