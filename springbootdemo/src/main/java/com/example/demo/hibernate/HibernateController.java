package com.example.demo.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController
public class HibernateController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/hibernate")
	@ResponseBody
	String home() {
        User user = userRepository.findByNickname("tom");
		return "Hello World! id:"+user.getId();
	}
}
