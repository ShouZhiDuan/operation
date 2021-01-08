package com.nvxclouds.operation.biz.utils;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/8/19 16:26
 * @Description: 常用字符串操作类
 */
public class StringUtils {

    /**
     * 数字不足位数前后补0
     * @param num 参与补零的数字
     * @param len 最终位数长度
     * @param prev 前或后，true前，false后
     * @return
     */
    public static String zeroFormat(Integer num, int len, boolean prev) {
        int l = num.toString().length();
        StringBuffer sb = new StringBuffer();
        if(!prev)
            sb.append(num);//后补
        for(int i=0;i<len-l;i++)
            sb.append("0");
        if(prev)
            sb.append(num);//前补
        return sb.toString();
    }

      //前补零
//    String str1 = String.format("%05d", 12);
//    System.out.println(str1);//00012

      //后补零
//    String num = "12";
//    int len = 5;
//    String str2 = num + String.format("%0"+(len - num.length())+"d", 0);
//    System.out.println(str2);//12000

}
