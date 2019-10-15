package com.lyao.mo.container.interceptor;

import com.lyao.mo.common.utils.CommonUtils;
import com.lyao.mo.common.utils.Constant;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author lyao
 * 验证访问权限
 * 对authc结尾的url进行拦截
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = Logger.getLogger(AuthenticationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String go_url = request.getServletPath().substring(1);
		String requestMethod = request.getMethod();
		log.warn("拦截"+requestMethod+"请求:" + go_url + " 进行登录校验");
		if (!CommonUtils.isOnline(request)){
			log.warn("访问被拒绝,登录失效或未登录");
			//判断是否是AJAX请求
			String requestType = request.getHeader("X-Requested-With");
			if("XMLHttpRequest".equals(requestType)){
				log.warn("AJAX请求..");
				response.setHeader("Session-Expire", "yes");
			}else{
				log.warn("非AJAX请求..");
				if (requestMethod.equalsIgnoreCase("GET")){
					request.getSession().setAttribute(Constant.GO_URL, go_url);
				}
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
