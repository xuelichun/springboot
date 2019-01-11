package com.bonc.test.aop1228;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 14:54 2018/12/28.
 * @Modified By:
 */
public class MonitionSession {

    private static ThreadLocal<MethodMonitor> monitorThreadLocal=new ThreadLocal<>();


    public static void begin(String method){
        MethodMonitor logger=new MethodMonitor(method);
        monitorThreadLocal.set(logger);

    }

    public static void end(){
        MethodMonitor logger=monitorThreadLocal.get();
        logger.log();
    }

}
