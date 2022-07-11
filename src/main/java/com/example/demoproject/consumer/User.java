package com.example.demoproject.consumer;

import com.example.demoproject.config.MessagingConfig;
import com.example.demoproject.dao.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message Recieved from Queue "+orderStatus);
    }
}
