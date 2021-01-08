package com.nvxclouds.operation.biz.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:11
 * @Description:区块链-区块面板top数据
 */

@Data
@Builder
@Table(name = "sp_block_chain_panel_top_data")
@NoArgsConstructor
@AllArgsConstructor
public class SpBlockChainPanelTopData {

    @Id
    @Column(name = "ID" )
    private Long id;
    //区块数量
    @Column(name = "block_amount")
    private Integer blockAmount;
    //交易数量
    @Column(name = "trade_amount")
    private Integer tradeAmount;
    //节点数量
    @Column(name = "peer_node_amount")
    private Integer peerNodeAmount;
    //链码数量
    @Column(name = "chain_amount")
    private Integer chainAmount;
    //同步更新时间
    @Column(name = "modify_time")
    private Date modifyTime;
    //入库创建时间
    @Column(name = "create_time")
    private Date createTime;
}
