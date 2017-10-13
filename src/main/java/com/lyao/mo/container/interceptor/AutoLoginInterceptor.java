package com.lyao.mo.container.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lyao.mo.bottom.service.SystemService;
import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.common.utils.Constant;
import com.lyao.mo.common.utils.CookiesUtil;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

	private final Logger log = Logger.getLogger(AutoLoginInterceptor.class);
	@Autowired
	private SystemService systemServiceImpl;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 1.只有未登录的用户才能自动登陆
		if (request.getSession().getAttribute(Constant.CURRENT_USER) == null) {
			//2 获取cookie
			String cookie_username = null;
			Cookie cookie = CookiesUtil.getCookieByName(request, Constant.COOKIE_USERNAME);
			if (cookie != null){
				cookie_username = URLDecoder.decode(cookie.getValue(), "utf-8");
				log.warn("用户：" + cookie_username + "自动登录");
				// 3 根据用户名查询当前用户信息并保存到session中
				if (StringUtils.isNoneBlank(cookie_username)){
					CurrentUser curuser = null;
					curuser = systemServiceImpl.selectUserByUsername(cookie_username);
					if (curuser != null){
						request.getSession().setAttribute(Constant.CURRENT_USER, curuser);
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