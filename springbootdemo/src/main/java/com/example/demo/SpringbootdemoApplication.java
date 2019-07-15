package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;
/**
 * 启动类
 * @author jaydelano
 *
 */
@SpringBootApplication //相当于@Configuration,@EnableAutoConfiguration和 @ComponentScan (默认搜索当前类所在包与其子包)
@PropertySource(value="classpath:my.yml",encoding="utf-8")
@ServletComponentScan //使用@ServletComponentScan注解后，Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码
@MapperScan("com.example.demo.mybatis.mapper")
public class SpringbootdemoApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringbootdemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
		log.info("项目启动");
	}

}

