package com.example.demo.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    @Autowired
    private Sender sender;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() throws Exception {
        sender.send();
        return "Hello World!";
    }
    
    @RequestMapping("/fanout")
    @ResponseBody
    public String fanout() throws Exception {
        sender.mybootfanoutExchange("通知######");
        return "Hello fanout!";
    }
    
}
