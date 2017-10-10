package com.lyao.mo.business.system.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lyao.mo.bottom.service.SystemService;
import com.lyao.mo.business.system.bo.CurrentUser;
import com.lyao.mo.business.system.bo.RegisterInfo;
import com.lyao.mo.common.utils.CommonUtils;
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
	/**
	 * 登录
	 * @param curuser
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/member/login",method = RequestMethod.POST)  
	@ResponseBody
	public ModelMap doLogin(@RequestParam String username, @RequestParam String password, 
			@RequestParam String validCode, HttpServletRequest request){ 
		ModelMap md = new ModelMap();
		String errorMsg = null;
		String flag = null;
		String go_url = null;
		String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (!kaptchaExpected.equalsIgnoreCase(validCode)){
			try {
				HashMap<String, Object> resultMap = systemServiceImpl.doLogin(username, password);
				String resultFlag = MapUtils.getString(resultMap,"flag");
				//认证成功
				if (resultFlag.equals("1")){
					flag = "yes";
					go_url = CommonUtils.getGoURL(request);
					//保存登录信息
					request.getSession().setAttribute(Constant.CURRENT_USER, MapUtils.getObject(resultMap,"seluser"));
				//认证失败	
				}else{
					flag = "no";
					errorMsg = MapUtils.getString(resultMap,"errorMsg");
				}
			} catch (Exception e) {
				flag = "no";
				errorMsg = "登录异常";
				log.error("登录异常", e);
			}
		}else {
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
		String go_url = null;
		String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (!kaptchaExpected.equalsIgnoreCase(customer.getValidCode())){
			try {
				CurrentUser curuser = systemServiceImpl.selectUserByUsername(customer.getUsername());
				if (curuser != null){
					flag = "no";
					errorMsg = "账号已存在";
				}else{
					// 手机号注册
					if (customer.getRegisterType().equals("1")){
						registerFlag = systemServiceImpl.insertMemberByMobile(customer);
						if(registerFlag){
							flag = "yes";
							go_url = CommonUtils.getGoURL(request);
							//保存登录信息
							curuser = systemServiceImpl.selectUserByUsername(customer.getUsername());
							request.getSession().setAttribute(Constant.CURRENT_USER, curuser);
						}else{
							flag = "no";
							errorMsg = "注册失败";
						}
						// 邮箱注册
					}else if (customer.getRegisterType().equals("2")){
						registerFlag = systemServiceImpl.insertMemberByEmail(customer);
						if(registerFlag){
							flag = "yes";
						}else{
							flag = "no";
							errorMsg = "注册失败";
						}
					}
				}
			} catch (Exception e) {
				flag = "no";
				log.error("注册失败", e);
				errorMsg = "注册失败";
			}
		} else{
			log.error("验证码错误");
			errorMsg = "验证码错误";
			flag = "no";
		}
		if (StringUtils.isNotBlank(go_url)){
			md.put("go_url", go_url);
		}
		if (StringUtils.isNotBlank(errorMsg)){
			md.put("errorMsg", errorMsg);
		}
		md.put("flag", flag);
		md.put("registerType", customer.getRegisterType());
		return md;
	}
	/**
	 * 
	 * @param validCode
	 * @return
	 */
	@RequestMapping(value = "/member/activation/{validCode}/{customerID}",method = RequestMethod.GET)
	public ModelAndView doActivation(@PathVariable("validCode") String validCode, @PathVariable("customerID") String customerID, 
			HttpServletRequest request){
		ModelAndView md = new ModelAndView();
		String type = null;
		String warnMsg = null;
		String go_url = null;
		log.info("用户号:"+customerID+" 申请激活，激活码：" + validCode);
		try {
			boolean flag = systemServiceImpl.updateActivation(customerID, validCode);
			if (flag){
				type = "activationSuccess";
				go_url = CommonUtils.getGoURL(request);
				md.addObject("go_url", go_url);
				//保存登录信息
				CurrentUser curuser = systemServiceImpl.selectUserByID(customerID);
				request.getSession().setAttribute(Constant.CURRENT_USER, curuser);
			}else {
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
	 * @param type
	 * @param go_url
	 * @return
	 */
	@RequestMapping(value = "/member/systemTips/{type}",method = RequestMethod.GET)  
	public ModelAndView doSystemTips(@PathVariable("type") String type, @RequestParam(value="go_url",required=false) String go_url){ 
		ModelAndView md = new ModelAndView();
		md.addObject("type", type);
		md.addObject("go_url", go_url);
		md.setViewName("system/system_tips");
		return md;
	}
	
}
