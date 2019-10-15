package com.lyao.mo.business.system.controller;

import com.lyao.mo.business.system.bean.CurrentUser;
import com.lyao.mo.business.system.bean.RegisterInfo;
import com.lyao.mo.business.system.service.SystemService;
import com.lyao.mo.common.utils.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
/**
 * 
 * @author lyao
 *
 */
@Controller
public class SysController {

	private final Logger log = Logger.getLogger(SysController.class);
	@Autowired
	private SystemService systemServiceImpl;

	/**
	 * 网站首页 -- 转发到notepad/index 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView md = new ModelAndView();
		md.setViewName("forward:/notepad/index");
		return md;
	}

	/**
	 * 跳转至登录或注册页面
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/member/{type}", method = RequestMethod.GET)
	public ModelAndView doLogin(@PathVariable String type) {
		ModelAndView md = new ModelAndView();
		md.addObject("type", type);
		md.setViewName("login");
		return md;
	}

	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap doLogin(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String validCode,
			@RequestParam(value = "rememberMe", required = false) String rememberMe,
			@RequestParam(value = "from_url", required = false) String from_url,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag;
		String go_url = null;
		boolean isLegalCaptcha = CaptchaUtils.isLegalCaptcha(request, validCode);
		if (isLegalCaptcha) {
			try {
				HashMap<String, Object> resultMap = systemServiceImpl.doLogin(
						username, password);
				String resultFlag = MapUtils.getString(resultMap, "flag");
				// 认证成功
				if ("1".equals(resultFlag)) {
					flag = "yes";
					//页面弹出层登录
					if (StringUtils.isNotBlank(from_url)){
						go_url = from_url;
					}else {
						go_url = CommonUtils.getGoURL(request);
					}
					// 记录密码等
					if (StringUtils.isNotBlank(rememberMe) && "1".equals(rememberMe)) {
						CookiesUtils.setCookie(response,
								Constant.COOKIE_USERNAME, username, 60*60);
					}
					// 保存登录信息
					CommonUtils.addLoginUserToSession(request, (CurrentUser) MapUtils.getObject(resultMap, "seluser"));
					// 认证失败
				} else {
					flag = "no";
					errorMsg = MapUtils.getString(resultMap, "errorMsg");
				}
			} catch (Exception e) {
				flag = "no";
				errorMsg = "登录异常";
				log.error("登录异常", e);
			}
		} else {
			flag = "no";
			errorMsg = "验证码错误";
		}
		md.put("flag", flag);
		md.put("go_url", go_url);
		md.put("errorMsg", errorMsg);
		return md;
	}

	/**
	 * 注册
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/member/register", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap doRegister(RegisterInfo customer, HttpServletRequest request) {
		ModelMap md = new ModelMap();
		String errorMsg = null;
		boolean registerFlag;
		String flag = null;
		String go_url = null;
		String code = (String) request.getSession().getAttribute(Constant.SHORT_MESSAGE_CODE);
		if (customer.getShortMessageCode().equalsIgnoreCase(code)) {
			try {
				customer.setAlias(RandomName.getNickName(customer.getUsername()));
				// 手机号注册
				if ("1".equals(customer.getRegisterType())) {
					registerFlag = systemServiceImpl
							.insertMemberByMobile(customer);
					if (registerFlag) {
						flag = "yes";
						go_url = CommonUtils.getGoURL(request);
						// 保存登录信息
						CurrentUser curuser = systemServiceImpl
								.selectUserByUsername(customer
										.getUsername());
						CommonUtils.addLoginUserToSession(request, curuser);
					} else {
						flag = "no";
						errorMsg = "注册失败";
					}
					// 邮箱注册
				} else if ("2".equals(customer.getRegisterType())) {
					registerFlag = systemServiceImpl
							.insertMemberByEmail(customer);
					if (registerFlag) {
						flag = "yes";
					} else {
						flag = "no";
						errorMsg = "注册失败";
					}
				}
			} catch (Exception e) {
				flag = "no";
				log.error("注册失败", e);
				errorMsg = "注册失败";
			}
		} else {
			log.error("验证码错误");
			errorMsg = "验证码错误";
			flag = "no";
		}
		if (StringUtils.isNotBlank(go_url)) {
			md.put("go_url", go_url);
		}
		if (StringUtils.isNotBlank(errorMsg)) {
			md.put("errorMsg", errorMsg);
		}
		md.put("flag", flag);
		md.put("registerType", customer.getRegisterType());
		return md;
	}

	/**
	 * 查询手机号是否已经注册
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/system/mobile", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap checkMobile(@RequestParam String mobile) {
		ModelMap md = new ModelMap();
		String flag;
		String message;
		try {
			CurrentUser curuser = systemServiceImpl
					.selectUserByUsername(mobile);
			if (curuser != null) {
				flag = "yes";
				message = "手机号已存在";
			} else {
				flag = "no";
				message = "手机号不存在";
			}
		} catch (Exception e) {
			flag = "error";
			message = "查询异常,请稍后再试";
			log.warn("查询异常");
		}
		md.put("flag", flag);
		md.put("message", message);
		return md;
	}

	/**
	 * 邮箱注册后激活
	 * @param validCode
	 * @return
	 */
	@RequestMapping(value = "/member/activation/{validCode}/{customerID}", method = RequestMethod.GET)
	public ModelAndView doActivation(
			@PathVariable("validCode") String validCode,
			@PathVariable("customerID") String customerID,
			HttpServletRequest request) {
		ModelAndView md = new ModelAndView();
		String type;
		String warnMsg = null;
		String go_url;
		log.info("用户号:" + customerID + " 申请激活，激活码：" + validCode);
		try {
			boolean flag = systemServiceImpl.updateActivation(customerID,
					validCode);
			if (flag) {
				type = "activationSuccess";
				go_url = CommonUtils.getGoURL(request);
				md.addObject("go_url", go_url);
				// 保存登录信息
				CurrentUser curuser = systemServiceImpl
						.selectUserByID(customerID);
				CommonUtils.addLoginUserToSession(request, curuser);
			} else {
				type = "activationFailed";
				warnMsg = "激活失败";
			}
		} catch (Exception e) {
			log.error("激活失败", e);
			type = "activationFailed";
			warnMsg = "激活失败";
		}
		md.addObject("type", type);
		md.addObject("warnMsg", warnMsg);
		md.setViewName("system/system_tips");
		return md;
	}

	/**
	 * 系统内部信息提示。根据Type跳转不同的页面，展示不同的信息。
	 * 
	 * @param type
	 * @param go_url
	 * @return
	 */
	@RequestMapping(value = "/member/systemTips/{type}", method = RequestMethod.GET)
	public ModelAndView doSystemTips(@PathVariable("type") String type,
			@RequestParam(value = "go_url", required = false) String go_url) {
		ModelAndView md = new ModelAndView();
		md.addObject("type", type);
		md.addObject("go_url", go_url);
		md.setViewName("system/system_tips");
		return md;
	}

	/**
	 * 跳转到重置密码页面
	 * @return
	 */
	@RequestMapping(value = "/system/changepassword", method = RequestMethod.GET)
	public ModelAndView changePassword() {
		ModelAndView md = new ModelAndView();
		md.setViewName("system/change_password");
		return md;
	}

	/**
	 * 查询手机号及验证码是否有效
	 *
	 * @param validCode
	 * @return
	 */
	@RequestMapping(value = "/system/captcha", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap checkMobileCaptcha(HttpServletRequest request, String mobile, String validCode) {
		ModelMap md = new ModelMap();
		String flag = "no";
		String message = "验证码错误";
		boolean isLegalCaptcha = CaptchaUtils.isLegalCaptcha(request, validCode);
		if (isLegalCaptcha) {
			try {
				CurrentUser curuser = systemServiceImpl
						.selectUserByUsername(mobile);
				if (curuser != null) {
					flag = "yes";
				} else {
					flag = "no";
					message = "手机号不存在";
				}
			} catch (Exception e) {
				flag = "error";
				message = "查询异常,请稍后再试";
				log.warn("查询异常");
			}
		}
		md.put("flag", flag);
		md.put("message", message);
		return md;
	}
	/**
	 * 注册
	 *
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/system/pw", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap forgetPassword(RegisterInfo customer, HttpServletRequest request) {
		ModelMap md = new ModelMap();
		String flag = null;
		String errorMsg = null;
		String code = (String) request.getSession().getAttribute(Constant.SHORT_MESSAGE_CODE);
		if (customer.getShortMessageCode().equalsIgnoreCase(code)) {
			try {
				boolean updateFlag = systemServiceImpl.updatePasswordByUsername(customer);
				if (updateFlag) {
					flag = "yes";
				}
			} catch (Exception e) {
				flag = "no";
				log.error("更新失败", e);
				errorMsg = "更新失败";
			}
		} else {
			log.error("验证码错误");
			flag = "no";
			errorMsg = "验证码错误";
		}
		md.put("flag", flag);
		md.put("errorMsg", errorMsg);
		return md;
	}
	/**
	 * 退出操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public ModelAndView doLoginOut(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView md = new ModelAndView();
		String go_url = request.getHeader("Referer");
		HttpSession session = request.getSession();
        session.invalidate();
        CookiesUtils.delectCookieByName(request, response, Constant.COOKIE_USERNAME);
		md.setViewName("redirect:" + go_url);
		return md;
	}
}
