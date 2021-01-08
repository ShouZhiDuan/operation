package com.nvxclouds.operation.biz.utils;

import com.alibaba.fastjson.JSONObject;
import com.nvxclouds.auth.api.domain.User;
import com.nvxclouds.common.core.util.JwtUtils;
import com.nvxclouds.operation.biz.enums.ApiProviderExceptionEnum;
import com.nvxclouds.operation.biz.exception.ApiProviderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/5/21 14:56
 * @Description:关于用通用操作
 */
public class UserUtils {


    /**
     * 根据用户code获取当前登录用户的信息
     * @param
     * @param token
     * @return
     */
    public static JSONObject getLoginUserByToken(String token, StringRedisTemplate redisTemplate){
        String userCodeString = "";
        try {
             userCodeString = JwtUtils.decodeToken(token);
        }catch (Exception e){
            //token解析异常
            throw new ApiProviderException(ApiProviderExceptionEnum.PASS_TOKEN_ERRO);
        }
        JSONObject jsonObject = ParamUtils.stringToJSONObject(userCodeString);
        String userStr =  redisTemplate.opsForValue().get("auth-sp-user-" + jsonObject.get("user_name"));
        //User user =  JsonUtils.jsonToObject(userStr, User.class);
        JSONObject jsonObjectUser = JSONObject.parseObject(userStr);

        //获取当前登录的用户信息
        //String userString = redisTemplate.opsForValue().get(String.format(RedisConfig.USER_LOGIN_KEY,jsonObject.get("user_name").toString()));
        //User userLogin = JsonUtils.jsonToObject(userString,User.class);
        //if(ObjectUtils.isEmpty(userLogin)){throw new ApiProviderException(ApiProviderExceptionEnum.SESSION_TIMEOUT);}
        return jsonObjectUser;
    }

    /**
     * 根据原始pwd获取加密后的salt和加密后的密码
     */
    public static Map<String,String> getSaltEncryptPwd(String pwd){
        String salt = generatorSalt();
        //已加密
        String password = DigestUtils.md5Hex(pwd + salt);
        Map<String,String> map = new HashMap<String,String>();
        map.put("salt",salt);
        map.put("password",password);
        return map;
    }

    /**
     * 判断用户输入的密码格式是否正确
     */
    public static boolean checkPwd(String pwd){
        return  ! (pwd.length() < 6) ;
    }


    /**
     * 随机获取salt
     * @return
     */
    private static String generatorSalt() {
        return DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
    }

}
