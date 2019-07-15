package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("queuename");
    }
    
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("mybootfanoutExchange");
    }
    
    @Bean
    Binding bindingExchangeA(Queue helloQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(helloQueue).to(fanoutExchange);
    }

}
