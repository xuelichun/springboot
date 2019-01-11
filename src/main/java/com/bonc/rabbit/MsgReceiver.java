package com.bonc.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 15:02 2019/1/9.
 * @Modified By:
 */
@Component
public class MsgReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_B)
    public void process(String content) {
        logger.info("接收处理队列A当中的消息： " + content);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void processB(String content){
        System.out.println(Thread.currentThread().getName()
                + " 接收到来自hello.queue1队列的消息：" + content.toUpperCase());
    }

}
