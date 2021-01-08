package com.nvxclouds.operation.biz;

import com.alibaba.fastjson.JSON;
import com.nvxclouds.operation.biz.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/9/2 17:52
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTest {

    private String name;

    public static void main(String[] args) {
//            MyTest test = new MyTest("段守志");
//            System.out.println(JSON.toJSONString(test));
//            System.out.println(JSON.toJSONString(test).toString());

            String txt1 = "{\"name\":\"段守志\"}";
            String txt2 = "[{\"name\":\"段守志\"},{\"name\":\"段守志2\"}]";

            MyTest myTest = JsonUtils.jsonToObject(txt1,MyTest.class);
            List<MyTest> list2 = JsonUtils.jsonToObject(txt2,List.class);
            System.out.println();


    }



}
