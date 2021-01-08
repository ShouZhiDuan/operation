//package com.nvxclouds.operation.biz.task;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.nvxclouds.operation.biz.utils.DateUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import sun.applet.Main;
//import sun.rmi.runtime.Log;
//
//import java.security.PrivateKey;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.List;
//
///**
// * @Auther: ShouZhi@Duan
// * @Date: 2020/8/27 14:31
// * @Description: 日志收集
// */
//@Slf4j
//@Component
//public class LogTask {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Scheduled(fixedRate = 30000)
//    private void sycLog() throws ParseException {
//        List<RunLog> addList = new ArrayList<>();
//        for(;;){
//            String objectString = redisTemplate.opsForList().leftPop("sys_log");
//            if(objectString.equals("nil")){
//                 break;
//            }else {
//                //将上报的日志信息转成json对象
//                JSONObject object = JSONObject.parseObject(objectString);
//                //获取日志内容
//                String msg = object.getString("message");
//                if(StringUtils.isNotBlank(msg) && msg.contains("[USER]")){
//                    //开始转成数据对象，待入库
//                    RunLog runLog = genRunLog(msg);
//                    addList.add(runLog);
//                }else {
//                    continue;
//                }
//            }
//        }
//        if(!CollectionUtils.isEmpty(addList)){
//            //批处理完开始保存数据库
//
//        }
//    }
//
//
//    public RunLog genRunLog(String msg) throws ParseException {
//        //String msg = "I0827 04:20:54.061789 14233 VertigoRequestHandler.cpp:40]  [USER] Finished vertigo analysis";
//        RunLog logInfo = new RunLog();
//        String part1 = msg.substring(0,1);//截取日志级别
//        String part2 = msg.substring(1,msg.length());//截取日志文本
//        log.info("日志级别logLevel：" + part1);
//        List<String> stringList = Arrays.asList(part2.split("\\s+"));
//        //获取时间
//        String time = getSysYear() + (stringList.get(0) + " " + stringList.get(1)).trim();
//        //字符串时间转成TimeStamp
//        Timestamp timestamp = new Timestamp(DateUtil.parse(time,"yyyyMMdd HH:mm:ss.SSS").getTime()); log.info("日志输出时间logTime：" + timestamp);
//        //日志进程信息
//        String threadInfo = "进程:" + (stringList.get(2) + "-" + stringList.get(3)).trim(); log.info("线程信息：" + threadInfo);
//        //获取日志实际内容
//        String logTxt = msg.substring(msg.indexOf("[USER]")+6,msg.length()).trim();   log.info("日志内容:" + logTxt);
//        logInfo.setLogLevel(part1);//日志级别
//        logInfo.setLogTime(timestamp);//日志输出时间
//        logInfo.setLogTxt(logTxt);//日志实际有效文本内容
//        logInfo.setThreadInfo(threadInfo);//线程栈信息
//        logInfo.setSynTime(new Date(System.currentTimeMillis()));//同步时间
//        return logInfo;
//    }
//
//    private static   String getSysYear() {
//        Calendar date = Calendar.getInstance();
//        String year = String.valueOf(date.get(Calendar.YEAR));
//        return year;
//    }
//
//    public static void main(String[] args) throws ParseException {
//        String msg = "I0827 04:20:54.061789 14233 VertigoRequestHandler.cpp:40]  [USER] Finished vertigo analysis";
//        RunLog logInfo = new RunLog();
//        String part1 = msg.substring(0,1);//截取日志级别
//        String part2 = msg.substring(1,msg.length());//截取日志文本
//        log.info("日志级别logLevel：" + part1);
//        List<String> stringList = Arrays.asList(part2.split("\\s+"));
//        //获取时间
//        String time = getSysYear() + (stringList.get(0) + " " + stringList.get(1)).trim();
//        //字符串时间转成TimeStamp
//        Timestamp timestamp = new Timestamp(DateUtil.parse(time,"yyyyMMdd HH:mm:ss.SSS").getTime()); log.info("日志输出时间logTime：" + timestamp);
//        //日志进程信息
//        String threadInfo = "进程:" + (stringList.get(2) + "-" + stringList.get(3)).trim(); log.info("线程信息：" + threadInfo);
//        //获取日志实际内容
//        String logTxt = msg.substring(msg.indexOf("[USER]")+6,msg.length()).trim();   log.info("日志内容:" + logTxt);
//        logInfo.setLogLevel(part1);//日志级别
//        logInfo.setLogTime(timestamp);//日志输出时间
//        logInfo.setLogTxt(logTxt);//日志实际有效文本内容
//        logInfo.setThreadInfo(threadInfo);//线程栈信息
//        logInfo.setSynTime(new Date(System.currentTimeMillis()));//同步时间
//        System.out.println(JSON.toJSONString(logInfo));
//    }
//
//
//
//}