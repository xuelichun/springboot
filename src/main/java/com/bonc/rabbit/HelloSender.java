package com.bonc.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 09:37 2019/1/8.
 * @Modified By:
 */
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){

        String context="hello "+ new Date();
        System.out.println("Sender:"+ context);
        this.rabbitTemplate.convertAndSend("hello",context);

    }
}
