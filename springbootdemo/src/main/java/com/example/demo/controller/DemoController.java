package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.DemoReq;

//@RestController = @Controller + @ResponseBody 默认直接返回json
@RestController
public class DemoController {
	
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @GetMapping("/demo/valid")
    public String demoValid(@Valid DemoReq req) {
        return req.getCode() + "," + req.getName();
    }
}
