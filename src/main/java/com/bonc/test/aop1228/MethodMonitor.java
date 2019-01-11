package com.bonc.test.aop1228;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 14:44 2018/12/28.
 * @Modified By:
 */
public class MethodMonitor {

    private long start;
    private String method;


    public MethodMonitor(String method) {
        this.method = method;
        System.out.println("begin monitor...");
        this.start = System.currentTimeMillis();

    }

    public void log(){
        long time=System.currentTimeMillis()-start;
        System.out.println("end monitor...");
        System.out.println("Method: "+ method+",execution time: "+time);
    }

}
