package com.example.demoproject.publisher;

import com.example.demoproject.config.MessagingConfig;
import com.example.demoproject.dao.Order;
import com.example.demoproject.dao.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restrorentName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restrorentName){

        order.setOrderId(UUID.randomUUID().toString());
        //restrorentService
        //paymentService
        OrderStatus status = new OrderStatus(order, "PROGRESS","order palced successfully in "+restrorentName);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTINGKEY, order);
        return "SUCCESS!!!";

    }

}
