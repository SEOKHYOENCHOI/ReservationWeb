/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.github.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @description : /api/reservations에 접근 시 Session에 Email 정보를 확인 후 없으면 Redirect
 * @package : com.nts.interceptor
 * @filename : SessionInterceptor.java
 * @author : 최석현
 * @method : boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
 */
public class SessionInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("reservationEmail");
		
		if(StringUtils.isEmpty(email)) {
			response.sendRedirect("/bookinglogin");
			return false;
		}
		
		return true;
	}
}