/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.interceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @description : Web Brower 접근 시 Console에 로그를 출력하는 Interceptor
 * @package : com.github.reservation.interceptor
 * @filename : LogInterceptor.java
 * @author : 최석현
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.getRemoteAddr();
		request.getRequestURL();
		
		String logMessage = "\n\n--------------Web Browser Request Info--------------"
						  	+ "\nClient IP Address : {}"
						  	+ "\nRequest URL : {}"
						  	+ "\nRequest Time : {}"
						  	+ "\n----------------------------------------------------\n";
		
		String clientIpAddr = request.getRemoteAddr();
		StringBuffer requestURL = request.getRequestURL();
		String requestTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);
		
		logger.info(logMessage
				   ,clientIpAddr
				   ,requestURL
				   ,requestTime);
		
		return true;
	}
}
