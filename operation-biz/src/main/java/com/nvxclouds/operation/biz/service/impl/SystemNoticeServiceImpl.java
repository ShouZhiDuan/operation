package com.nvxclouds.operation.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.nvxclouds.common.core.service.AbstractService;
import com.nvxclouds.operation.biz.base.CommonPage;
import com.nvxclouds.operation.biz.domain.SystemNotice;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import com.nvxclouds.operation.biz.mapper.SystemNoticeMapper;
import com.nvxclouds.operation.biz.service.SystemNoticeService;
import com.nvxclouds.operation.biz.utils.UserUtils;
import com.nvxclouds.operation.biz.vo.SysNoticeQuery;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 13:07
 * @Description:
 */
@Service
public class SystemNoticeServiceImpl extends AbstractService<SystemNotice> implements SystemNoticeService {

    private  static  final List<Integer> type = Arrays.asList(1,2,3,4);

    @Autowired
    private SystemNoticeMapper systemNoticeMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * redisTemplate.opsForValue().set("auth-sp-user-" + user.getUserCode(), JSON.toJSONString(user));
     */

    @Override
    public Object addNotice(String token, SystemNotice notice) {
        //获取当前登录用户的信息
        JSONObject user = UserUtils.getLoginUserByToken(token,redisTemplate);
        notice.setUserName((String) user.get("userName"));
        notice.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        int count = systemNoticeMapper.insert(notice);//保存本地通知库
        // to do call->medusa同步
        return count;
    }


    @Override
    public SystemNotice noticeDetails(Long id) {
        SystemNotice notice = systemNoticeMapper.selectByPrimaryKey(id);
        if(ObjectUtils.isEmpty(notice)){
            throw  new ApiProviderException(ApiProviderExceptionEnum.NOT_EXIT_NOTICE);
        }
        return notice;
    }


    @Override
    public Object noticeList(SysNoticeQuery query) {
        PageHelper.startPage(query.page,query.getPerPage());
        List<SystemNotice> list =  systemNoticeMapper.getSysNoticeList(query);
        list.forEach(notice -> {
            //通知类型  1:Medusa  2:Skadi   3:监管系统  4:APP，多选则格式：1,2,3,4
            String noticeType = notice.getNoticeType();
            if(StringUtils.isNotBlank(noticeType) && noticeType.contains("\\,")){
                noticeType = "";
                List<String> typeList = Arrays.asList(noticeType.split("\\,"));
                for (String type : typeList) {
                     if(type.equals("1")){
                         noticeType = noticeType + "Medusa,";
                     }
                     if(type.equals("2")){
                         noticeType = noticeType + "Skadi,";
                     }
                     if(type.equals("3")){
                         noticeType = noticeType + "监管系统,";
                     }
                    if(type.equals("4")){
                        noticeType = noticeType + "APP,";
                    }
                }
                notice.setNoticeType(noticeType.substring(0,noticeType.length() - 1));
            }

        });
        CommonPage<SystemNotice> pageList = CommonPage.restPage(list);
        return pageList;
    }
}
