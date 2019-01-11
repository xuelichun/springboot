package com.bonc.test1217;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 17:21 2018/12/17.
 * @Modified By:
 */
public class Stream1217 {
    public static void main(String[] args) {
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();


        //数值流的构造
        //IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        //IntStream.range(1, 3).forEach(System.out::println);
        //IntStream.rangeClosed(1, 3).forEach(System.out::println);

        List<String> strList=Arrays.asList("aa","bb","cc","dd");
        List<String> output = strList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        System.out.println(JSON.toJSONString(output));
    }
}
