package com.example.demo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义监听器
 * @author jaydelano
 *
 */
@WebListener
public class CustomListener implements ServletRequestListener {

	
	private static final Logger log = LoggerFactory.getLogger(CustomListener.class);
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		log.info("监听器初始化");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		log.info("监听器销毁");
	}
	
}
