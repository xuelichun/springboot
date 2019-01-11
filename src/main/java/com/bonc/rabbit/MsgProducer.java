package com.bonc.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 15:08 2019/1/8.
 * @Modified By:
 */
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {


    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    /*public MsgProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;

        rabbitTemplate.setConfirmCallback(this);

    }*/

    /*1.@PostConstruct说明

    被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
            2.@PreDestroy说明

    被@PreDestroy修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet的destroy()方法。被@PreDestroy修饰的方法会在destroy()方法之后运行，在Servlet被彻底卸载之前。
*/  @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    //消息生产者
    public void sendMsg(String content){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        System.out.println("开始发送消息 : " + content.toLowerCase());

        /*
          rabbitTemplate.send(message);   //发消息，参数类型为org.springframework.amqp.core.Message
          rabbitTemplate.convertAndSend(object); //转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
          rabbitTemplate.convertSendAndReceive(message) //转换并发送消息,且等待消息者返回响应消息。
        */
         //String response=rabbitTemplate.convertSendAndReceive(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_A,content,correlationId).toString();
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_B,content,correlationId);
        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_B,content,correlationId);
    }

    /**
     * 回调
     * @param correlationData
     * @param b
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b){
            System.out.println("消费成功！！");
        }else{
            System.out.println("消费失败"+s);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(message.getMessageProperties().getCorrelationId()+ " 发送失败");
    }
}
