package com.nvxclouds.operation.biz.service;

import com.nvxclouds.common.core.service.Service;
import com.nvxclouds.operation.biz.base.BaseQuery;
import com.nvxclouds.operation.biz.domain.pgsql.DataNodes;
import com.nvxclouds.operation.biz.dto.*;
import com.nvxclouds.operation.biz.vo.DataSortQuery;
import com.nvxclouds.operation.biz.vo.medusa_vo.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/6/23 15:41
 * @Description:数据节点服务
 */
public interface DataNodesService extends Service<DataNodes> {

    /**
     * 查询数据节点列表
     * @param query
     */
    Object list(DataNodesQuery query) throws ParseException;

    /**
     * 查询数据节点详情
     * @param id
     */
    DataNodeDetailsDTO details(Long id);


    /**
     * 数据节点暂停恢复操作
     * @param dto
     */
    void suspended(DataNodesSuspendedDTO dto) throws IOException;

    /**
     * 黑名单操作
     * @param dto
     */
    void blacked(DataNodesBlackedDTO dto) throws IOException;

    /**
     * 查询数据节点报表根据数据节点状态
     * @param
     */
    Object repor();

    /**
     * 查询数据集列表
     * @param query
     * @return
     */
    Object setList(DataSetsQuery query);

    /**
     * 系统监控
     * @param query 节点名称
     * @return
     */
    Object resources(ResourcesQuery query);

    /**
     * 数据集状态报表
     * @return
     */
    Object reporSet();

    /**
     * 数据节点审批
     */
    void approval(Long dataNodeId);

    /**
     *  数据节点恢复操作
     */
    void regain(Long dataNodeId,Integer type);

    /**
     * 数据集恢复操作
     */
    void regainSet(Long dataSetId,Integer type);

    /**
     * 数据集使用日、月统计列表
     */
    Object dayMonth(Integer type, BaseQuery query);

    /**
     * 统计研究方法数据集使用数量
     */
    Object methodDataSetCount();

    /**
     *  黑名单列表查询
     * 1表示个人研究者黑名单
     * 2表示企业研究者黑名单
     * 3表示数据提供者黑名单
     */
    Object blackNameList(DataNodesBlackNameListQuery query);

    /**
     * 黑名单详情查询
     * 1表示个人研究者黑名单
     * 2表示企业研究者黑名单
     * 3表示数据提供者黑名单
     */
    Object details(Integer type, Integer id);

    /**
     * 黑名单取消操作
     * 1表示个人研究者黑名单
     * 2表示企业研究者黑名单
     * 3表示数据提供者黑名单
     */
    Object cancleBlackName(Integer type, Integer id);

    /**
     * 数据集历史使用列表查询
     * @param query
     */
    Object usedCountList(UsedCountQuery query);

    /**
     * 查询数据提供方黑名单列表(有效在用)
     * @param query
     * @return
     */
    Object queryDataNodeBlackNameList(BaseQuery query);

    /**
     * 数据节点黑名单详情
     * @param id
     * @return
     */
    Object queryDataNodeBlackNameDetails(Long id);

    /**
     * 取消数据节点黑名单操作
     * @param cancleBlackDTO
     */
    void cancelDataNodeBlack(CancleBlackDTO cancleBlackDTO);

    /**
     * 查询正在运行的研究列表
     * @param query
     * @return
     */
    Object queryRunningStudies(BaseQuery query);


    /**
     * 数据提供方排名查询
     * @param query
     * @return
     */
    Object topXDataProvider(DataSortQuery query);

    /**
     * 数据集暂停、恢复操作
     */
    void regainSetNew(RegainSuspendedDTO dto) throws IOException;
}
