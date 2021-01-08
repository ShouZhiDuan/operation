package com.nvxclouds.operation.biz.constants;

import com.nvxclouds.operation.biz.config.medusa_study.StudyServerConfig;
import org.springframework.stereotype.Component;

/**
 * @Author：ShouZhi@Duan
 */

@Component
public class EnvironmentVariables {

    /**
     * 获取数据节点黑名单服务地址
     */
    public String getDataNodeBlockedUrl(){
        return StudyServerConfig.STUDY_SERVER_URL + Constants.DATA_NODE_BLACED_PATH;
    }

    /**
     * 获取数据节点暂停服务地址
     */
    public String getDataNodeSuspendUrl(){
        return StudyServerConfig.STUDY_SERVER_URL + Constants.DATA_NODE_SUSPENDED_PATH;
    }

    /**
     * 获取数据节点恢复服务地址
     */
    public String getDataNodeRegainUrl(){
        return StudyServerConfig.STUDY_SERVER_URL + Constants.DATA_NODE_REGAIN_PATH;
    }

    /**
     * 获取数据集暂停服务地址
     */
    public String getDataSetSuspendUrl(){
        return StudyServerConfig.STUDY_SERVER_URL + Constants.DATA_SET_SUSPENDED_PATH;
    }

    /**
     * 获取数据集恢复服务地址
     */
    public String getDataSetRegainUrl(){
        return StudyServerConfig.STUDY_SERVER_URL + Constants.DATA_SET_REGAIN_PATH;
    }

    /**
     * 获取研究者黑名单操作服务地址
     */
    public String getResearcherBlacedUrl(){
        return StudyServerConfig.LICENSE_SERVER_URL + Constants.RESEARCHER_BLACED_PATH;
    }

    /**
     * 获取研究者黑名单取消服务地址
     */
    public String getResearcherCancelUrl(){
        return StudyServerConfig.LICENSE_SERVER_URL + Constants.RESEARCHER_CANCEL_PATH;
    }



}
