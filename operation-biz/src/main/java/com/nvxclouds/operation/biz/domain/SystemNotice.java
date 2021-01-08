package com.nvxclouds.operation.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/2 16:06
 * @Description: 系统通知
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_notice")
public class SystemNotice {

    @Id
    @Column(name = "ID" )
    private Long id;


    @NotBlank(message = "标题不能为空")
    @Column(name = "title" )
    private String title;


    @NotBlank(message = "副标题不能为空")
    @Column(name = "sub_title" )
    private String subTitle;


    @NotBlank(message = "消息内容不能为空")
    @Column(name = "content" )
    private String content;


    @NotNull(message = "请选择通知系统")
    @Column(name = "notice_type" )
    private String noticeType;//通知类型  1:Medusa  2:Skadi   3:监管系统  4:APP



    @Column(name = "created_time" )
    private Timestamp createdTime;



    @Column(name = "user_name" )
    private String userName;
}
