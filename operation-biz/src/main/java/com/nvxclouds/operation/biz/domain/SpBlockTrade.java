package com.nvxclouds.operation.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:11
 * @Description:区块链-所有交易数据
 */

@Data
@Builder
@Table(name = "sp_block_trade")
@NoArgsConstructor
@AllArgsConstructor
public class SpBlockTrade {
    @Id
    @Column(name = "ID" )
    private Long id;
    //操作名称
    @Column(name = "operation")
    private String operation;
    //节点ID
    @Column(name = "data_node_id")
    private String dataNodeId;
    //交易时间 区块链中字符串格式
    @Column(name = "tx_time")
    private String txTime;
    //节点名称
    @Column(name = "node_name")
    private String nodeName;
    //操作数据hash
    @Column(name = "message_hash")
    private String messageHash;
    //状态
    @Column(name = "status")
    private String status;
    //交易ID全局唯一
    @Column(name = "transaction_id")
    private String transactionId;
    //组织类型    1：数据提供方  2：APP用户  3：数据适用方  4：监管方  5：应用提供方
    @Column(name = "organization")
    private Integer organization;
    //交易时间由字段txTime字符创转换过来存储，便于数据库时间上的操作
    @Column(name = "trade_time")
    private Timestamp tradeTime;
    //交易时间由字段txTime字符创转换过来存储，便于数据库时间上的操作
    @Column(name = "create_time")
    private Date createTime;
}
