package com.lyao.mo.business.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lyao.mo.bottom.service.SystemService;
import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.business.system.bo.RegisterInfo;
import com.lyao.mo.common.utils.Constant;

@Controller
public class SysController {
	
	private final Logger log = Logger.getLogger(SysController.class);
	@Autowired
	private SystemService systemServiceImpl;
	/**
	 * 网站首页
	 * @return
	 */
	@RequestMapping(value = "/",method = RequestMethod.GET)  
	public ModelAndView index(){ 
		ModelAndView md = new ModelAndView();
		md.setViewName("notepad/notepad");
		return md;
	} 
	/**
	 * 跳转至登录或注册页面
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/member/{type}",method = RequestMethod.GET)  
	public ModelAndView doLogin(@PathVariable String type){ 
		ModelAndView md = new ModelAndView();
		md.addObject("type", type);
		md.setViewName("login");
		return md;
	}
	
	@RequestMapping(value = "/member/login",method = RequestMethod.POST)  
	@ResponseBody
	public ModelMap doLogin(CurrentUser curuser, HttpServletRequest request){ 
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		String go_url = "/" ;
		Object target_url = request.getSession().getAttribute(Constant.GO_URL);
		if (target_url != null){
			go_url = (String) target_url;
			flag = "yes";
		}
		md.put("go_url", go_url);
		md.put("flag", flag);
		return md;
	} 
	/**
	 * 注册
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/member/register",method = RequestMethod.POST)
	@ResponseBody
	public ModelMap doRegister(RegisterInfo customer, HttpServletRequest request){ 
		ModelMap md = new ModelMap();
		String errorMsg = null;
		boolean registerFlag = false;
		String flag = null;
		String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (kaptchaExpected.equalsIgnoreCase(customer.getValidCode())){
			// 手机号注册
			if (customer.getRegisterType().equals("1")){
				try {
					registerFlag = systemServiceImpl.insertMemberByMobile(customer);
					if(registerFlag){
						flag = "yes";
						
					}else{
						flag = "no";
						errorMsg = "注册失败";
					}
				} catch (Exception e) {
					log.error("注册失败", e);
					errorMsg = "注册失败";
				}
				// 邮箱注册
			}else if (customer.getRegisterType().equals("2")){
				try {
					registerFlag = systemServiceImpl.insertMemberByEmail(customer);
					if(registerFlag){
						flag = "yes";
					}else{
						flag = "no";
						errorMsg = "注册失败";
					}
				} catch (Exception e) {
					log.error("注册失败", e);
					errorMsg = "注册失败";
				}
			}
		} else{
			log.error("验证码错误");
			errorMsg = "验证码错误";
			flag = "no";
		}
		if (StringUtils.isNotBlank(errorMsg)){
			md.put("errorMsg", errorMsg);
		}
		md.put("flag", flag);
		return md;
	}
	/**
	 * 
	 * @param validCode
	 * @return
	 */
	@RequestMapping(value = "/member/activation/{validCode}/{customerID}",method = RequestMethod.GET)
	public ModelAndView doActivation(@PathVariable("validCode") String validCode, @PathVariable("customerID") String customerID){
		ModelAndView md = new ModelAndView();
		log.info(validCode+"    "+customerID);
		md.setViewName("notepad/notepad");
		return md;
	}
	
}
