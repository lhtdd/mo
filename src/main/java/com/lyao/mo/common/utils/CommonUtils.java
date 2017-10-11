package com.lyao.mo.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyao.mo.business.system.bo.CurrentUser;

public class CommonUtils {

	/**
	 * 获取Session中保存的当前用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static CurrentUser getCurrentUser(HttpServletRequest request) {
		Object curuser = request.getSession().getAttribute(
				Constant.CURRENT_USER);
		CurrentUser currentUser = null;
		if (curuser != null) {
			currentUser = (CurrentUser) curuser;
		}
		return currentUser;
	}

	/**
	 * 获取跳转的URL
	 * 
	 * @param request
	 * @return
	 */
	public static String getGoURL(HttpServletRequest request) {
		String go_url = null;
		Object target_url = request.getSession().getAttribute(Constant.GO_URL);
		if (target_url != null) {
			go_url = (String) target_url;
		} else {
			// 如果没有拦截的URL 则跳转至 首页
			go_url = request.getContextPath() + "/";
		}
		return go_url;
	}
	/**
	 * 保存登录名，密码用以自动登录，有效期1天
	 * @param response
	 * @param userName
	 * @param password
	 * @throws UnsupportedEncodingException
	 */
	public static void saveLoginCookies(HttpServletResponse response,
			String username, String password) throws UnsupportedEncodingException {
		Cookie cookie1 = new Cookie(Constant.COOKIE_USERNAME, URLEncoder.encode(
				username, "UTF-8"));
		Cookie cookie2 = new Cookie(Constant.COOKIE_PASSWORD, URLEncoder.encode(
				password, "UTF-8"));
		cookie1.setMaxAge(24 * 60 * 60);
		cookie2.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
	}
}
