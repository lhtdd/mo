package com.lyao.mo.container.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.common.utils.CommonUtils;
import com.lyao.mo.common.utils.Constant;


public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = Logger.getLogger(AuthenticationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String go_url = request.getServletPath().substring(1);
		log.warn("拦截请求:" + go_url + " 进行登录校验");
		CurrentUser curUser = null;
		curUser = CommonUtils.getCurrentUser(request);
		if (curUser == null){
			log.warn("访问被拒绝,登录失效或未登录");
			// 如果请求方法是POST 则不保存该url。
			if (!request.getMethod().equals("POST")){
				request.getSession().setAttribute(Constant.GO_URL, go_url);
			}
			String requestType = request.getHeader("X-Requested-With");
			//判断是否是AJAX请求
			if("XMLHttpRequest".equals(requestType)){
				log.warn("AJAX请求..");
			    response.getWriter().write("no");
			}else{
				log.warn("非AJAX请求..");
				//非ajax请求，例如从浏览器直接输入则跳转至整个登录页面
				request.getRequestDispatcher("/member/login").forward(request, response);
			}
			return false;
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
