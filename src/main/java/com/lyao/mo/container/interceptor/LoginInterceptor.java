package com.lyao.mo.container.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lyao.mo.common.utils.Constant;


/**
 * @author lyao
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = Logger.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String go_url = request.getHeader("Referer");
		log.warn("请求来自：" + go_url);
		//从浏览器直接访问可能为NULL
		if (go_url == null){
			return true;
		}
		// POST请求直接放过
		if (request.getMethod().equalsIgnoreCase("POST")){
			return true;
		}
		String historyURL = (String) request.getSession().getAttribute(Constant.GO_URL);
		if ( historyURL != null){
			if (go_url.endsWith("/member/login") || go_url.endsWith("/member/register") || historyURL.equals(go_url)){
				return true;
			}
		}
		// 记录来时的URL
		request.getSession().setAttribute(Constant.GO_URL, go_url);
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
