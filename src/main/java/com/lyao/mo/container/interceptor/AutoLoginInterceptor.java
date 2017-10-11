package com.lyao.mo.container.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lyao.mo.common.utils.Constant;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

	private final Logger log = Logger.getLogger(AutoLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 1.只有未登录的用户才能自动登陆
		if (request.getSession().getAttribute(Constant.CURRENT_USER) == null) {
			String cookie_username = null;
			String cookie_password = null;
			// 获得cookie
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					// 获得名字是cookie_username和cookie_password
					if (Constant.COOKIE_USERNAME.equals(cookie.getName())) {
						cookie_username = URLDecoder.decode(cookie.getValue(),"UTF-8");
					}
					if (Constant.COOKIE_PASSWORD.equals(cookie.getName())) {
						cookie_password = URLDecoder.decode(cookie.getValue(),"UTF-8");
					}
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
