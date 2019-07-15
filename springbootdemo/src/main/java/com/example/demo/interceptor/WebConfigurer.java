package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

	@Autowired
	private CustomHandlerInterceptor customHandlerInterceptor;

	// 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器 拦截规则
		// 多个拦截器时 以此添加 执行顺序按添加顺序
		registry.addInterceptor(customHandlerInterceptor).addPathPatterns("/*").excludePathPatterns("/login", "/register");
	}

	// 这个方法是用来配置静态资源的，比如html，js，css，等等
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

}
