package com.lyao.mo.container.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lyao.mo.common.utils.Constant;


public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = Logger.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// POST请求直接放过
		if (request.getMethod().equalsIgnoreCase("POST")){
			return true;
		}
		log.warn("请求来自：" + request.getHeader("Referer"));
		// 记录来时的URL
		request.getSession().setAttribute(Constant.GO_URL, request.getHeader("Referer"));
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
