package com.nvxclouds.operation.biz;

import org.springframework.util.DigestUtils;

/**
 * @Auther: zhengxing.hu
 * @Date: 2020/6/11 17:22
 * @Description:
 */
public class Test {
    public static void main(String args[]){
        // 获取当前系统所在服务器的时间，毫秒单位
        // 注意，当前系统不一定是 Nginx 所在服务器
//        System.out.println("当前系统时间："+ System.currentTimeMillis());
        long milliseconds = System.currentTimeMillis();
        // 添加有效期时间，假设该链接有效期为 1 天，即 86400000
        // 计算毫秒时，切记转换为 Long 类型进行运算，避免超出 int 类型的范围
        // 自己测试时，为了方便，可以设置为 1 分钟之类的
        System.out.println("当前系统时间：" + milliseconds);
        milliseconds += 1L * 24 * 60 * 60 * 1000;
        // milliseconds += 60L * 1000;

        // 请求的资源路径
        // 计算 token 信息
        String requestResources = "/tensorboard/start";
        // “盐” 值，和 Nginx 服务器上的保持一致即可
        String salt = "41bd2fce-645f-40cf-aa0e-67a9eaeeee32";
        // 加密前的字符串：请求的资源路径 + “盐” 值 + 时间戳
        String beforeEncryptionString = requestResources + salt + milliseconds;
        // 这里使用 Spring 提供的 md5 加密工具进行 md5 加密
        String token = DigestUtils.md5DigestAsHex(beforeEncryptionString.getBytes());
        String url = requestResources + "?ts=" + milliseconds + "&token=" + token;

        System.out.println("请求的 url 为：");
        System.out.println(url);
    }

}
