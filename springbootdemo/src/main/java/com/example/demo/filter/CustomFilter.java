package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * 自定义过滤器
 * @author jaydelano
 *
 */
@WebFilter(filterName="customFilter",urlPatterns={"/*"})//注册器名称为customFilter,拦截的url为所有
@Order(1) //指定过滤器执行顺序
public class CustomFilter implements Filter{
	
	private static final Logger log = LoggerFactory.getLogger(CustomFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("filter 初始化");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        log.info("doFilter 请求处理");
        //链路 直接传给下一个过滤器
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.info("filter 销毁");
	}
}
