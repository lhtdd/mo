package com.lyao.mo.container.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.common.utils.Constant;


public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = Logger.getLogger(AuthenticationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.warn("拦截请求:" + request.getServletPath() + " 进行登录校验");
		CurrentUser curUser = null;
		curUser = (CurrentUser) request.getSession().getAttribute(Constant.CURRENT_USER);
		if (curUser == null){
			response.getWriter().write("no");
			log.warn("访问被拒绝,登录失效或未登录");
			return false;
		}else{
			
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
