package com.bonc.list;

import org.mozilla.javascript.ast.ForInLoop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 09:42 2018/12/20.
 * @Modified By:
 */
public class ListTest {


    public static long getLinkedTime(){
        long start = System.currentTimeMillis();
        LinkedList<String> linkedList=new LinkedList<String>();
        for (int i = 0; i <700000 ; i++) {
            //linkedList.add("aaa");
            linkedList.add(i,"aaa");
        }
        long end = System.currentTimeMillis();
        return end-start;
    }


    public static long getListTime(){
        long start = System.currentTimeMillis();
        List<String> list=new ArrayList<String>();
        for (int i = 0; i <700000 ; i++) {
            //list.add("aaa");
            list.add(i,"aaa");
        }
        long end = System.currentTimeMillis();
        list.subList(0,1).clear();
        return end-start;
    }

    public static void main(String[] args) {
        //System.out.println("linktime:"+getLinkedTime());
        //System.out.println("listtime:"+getListTime());
        //List<List<Integer>> cache = new ArrayList<List<Integer>>();
        //内存溢出
        /*try {
            while (true) {
                List<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j < 100000; j++) {
                    list.add(j);
                }

                List<Integer> sublist = list.subList(0, 1);
                cache.add(sublist);
            }
        } finally {
            System.out.println("cache size = " + cache.size());
        }*/

        //sublist截取索引越界异常

        List<String> list=new ArrayList();
        for (int i = 0; i <10 ; i++) {
            list.add("a");
        }
        for (int i = 0; i <10 ; i++) {
            System.out.println(list.get(i));
        }
        for(String str:list){
            System.out.println(str);
        }
        //System.out.println(list.subList(5,12));
        System.out.println("截取前"+list);
        list.subList(0,2).clear();
        System.out.println("截取后"+list);

    }
}
