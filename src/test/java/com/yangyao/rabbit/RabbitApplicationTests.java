package com.yangyao.rabbit;

import com.yangyao.rabbit.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 1.单播（点对点）
     * rabbitTemplate.send(exchange,routeKey,message);//需要自己构造一个，定义消息体内容和消息头
     *
     * rabbitTemplate.convertAndSend(exchange,routingKey,Object);//默认当成消息体，只需要传入要发送的对象，自动序列化发给rabbitmq;
     */
    @Test
    public void contextLoads() {
        Map<String,Object> map=new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("hellowworld","123","换行"));
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
    }

    //接收数据，如何使用json序列化
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }

}
