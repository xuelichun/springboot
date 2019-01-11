package com.bonc.test1217;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 09:20 2018/12/17.
 * @Modified By:
 */
public class DuoTai {
    void run(){
        System.out.println("car running");
    }
}


class Audi extends  DuoTai{
    @Override
    void run(){
        System.out.println("audi running");
    }
    public static void main(String[] args) {
        DuoTai dt=new Audi();
        dt.run();
    }
}



