package com.cl.user.serviceImpl;

import org.springframework.stereotype.Service;

import com.cl.user.servicei.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	public String sayHello() {
		
		System.out.println("hello world2----------------------------");
		
		return "hello world2=====";
		
	}
}
