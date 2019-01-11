package com.bonc.test;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 10:21 2019/1/11.
 * @Modified By:
 */
public class ClassInit {

    private static ClassInit classInit = new ClassInit();
    public    int value1;
    public  int value2 = 0;
    private ClassInit(){
        value1++; value2++;
    }
    public static ClassInit getInstance(){
        return classInit;
    }
    public static void main(String[] args) {
        ClassInit singleton = ClassInit.getInstance();
        System.out.println("Singleton1 value1:" + singleton.value1);
        System.out.println("Singleton1 value2:" + singleton.value2);
        Singleton2 singleton2 = Singleton2.getInstance2();
        System.out.println("Singleton2 value1:" + singleton2.value1);
        System.out.println("Singleton2 value2:" + singleton2.value2);

    }
}


class Singleton2{
    public  int value1;
    public  int value2 = 0;
    private static Singleton2 singleton2 = new Singleton2();
    private Singleton2(){ value1++; value2++; }
    public static Singleton2 getInstance2(){
        return singleton2;
    }
}

