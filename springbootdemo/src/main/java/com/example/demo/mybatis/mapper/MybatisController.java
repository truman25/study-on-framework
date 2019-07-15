package com.example.demo.mybatis.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {

	@Autowired
    private UserMapper userMapper;

	@RequestMapping("/add")
	@ResponseBody
	public void testInsert() throws Exception {
        userMapper.insert(new Users((short)0, "tom2", "43534", "345346"));
        userMapper.insert(new Users((short)1, "tom3", "5555555", "345346"));
    }

	@RequestMapping("/query")
	@ResponseBody
    public void testQuery() throws Exception {
        List<Users> users = userMapper.getAll();
        System.out.println(users.toString());
    }

	@RequestMapping("/update")
	@ResponseBody
    public void testUpdate() throws Exception {
        Users user = userMapper.getOne(1l);
        System.out.println(user.toString());
        user.setNickName("neo");
        userMapper.update(user);
    }
}
