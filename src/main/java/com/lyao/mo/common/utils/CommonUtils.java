package com.lyao.mo.common.utils;

import com.lyao.mo.business.system.bean.CurrentUser;

import javax.servlet.http.HttpServletRequest;

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

	public static void addLoginUserToSession(HttpServletRequest request, CurrentUser currentUser){
		request.getSession().setAttribute(Constant.CURRENT_USER, currentUser);
	}

	/**
	 * 判断当前用户是否在线
	 * @param request
	 * @return
	 */
	public static boolean isOnline(HttpServletRequest request){
		CurrentUser currentUser = getCurrentUser(request);
		if (currentUser != null){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 获取跳转的URL
	 * 
	 * @param request
	 * @return
	 */
	public static String getGoURL(HttpServletRequest request) {
		String go_url;
		Object target_url = request.getSession().getAttribute(Constant.GO_URL);
		if (target_url != null) {
			go_url = (String) target_url;
		} else {
			// 如果没有拦截的URL 则跳转至 首页
			go_url = request.getContextPath() + "/";
		}
		return go_url;
	}
}
