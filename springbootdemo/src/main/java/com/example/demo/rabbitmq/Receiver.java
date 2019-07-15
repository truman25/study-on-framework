package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queuename")
public class Receiver {

	@RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
