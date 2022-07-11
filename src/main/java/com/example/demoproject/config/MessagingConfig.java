package com.example.demoproject.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {


    public static final String ROUTINGKEY = "myRoutingKey";
    public static final String EXCHANGE = "myExchange";
    public static final String QUEUE = "myQueue";


    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    public RabbitTemplate rubeExchangeTemplate() {
        RabbitTemplate r = new RabbitTemplate(rabbitConnectionFactory);
        r.setExchange("rmq.rube.exchange");
        r.setRoutingKey("rube.key");
        r.setConnectionFactory(rabbitConnectionFactory);
        return r;
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY);

    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate =new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
