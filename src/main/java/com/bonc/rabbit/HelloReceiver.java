package com.bonc.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 09:42 2019/1/8.
 * @Modified By:
 */

@Component
//@RabbitListener(queues="hello")
public class HelloReceiver {

    @RabbitHandler
    public void pricess(String hello){
        System.out.println("Receiver:"+hello);
    }
}
