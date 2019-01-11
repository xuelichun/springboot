package com.bonc.test.aop1228;

import org.springframework.stereotype.Service;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 14:41 2018/12/28.
 * @Modified By:
 */
@Service
public class PersonSpring implements Speakable {
    @Override
    public void sayHi() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hi !!!");

    }

    @Override
    public void sayBye() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bye !!!");
    }
}
