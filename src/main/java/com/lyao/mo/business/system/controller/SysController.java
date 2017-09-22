package com.lyao.mo.business.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lyao.mo.bottom.pojo.RegisterInfo;
import com.lyao.mo.bottom.service.SystemService;
import com.lyao.mo.business.system.vo.T_customer;

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
	 * 注册
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/member/register",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView doRegister(RegisterInfo customer, HttpServletRequest request){ 
		ModelAndView md = new ModelAndView();
		String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (kaptchaExpected.equalsIgnoreCase(customer.getValidCode())){
			if (customer.getRegisterType().equals("1")){
				try {
					systemServiceImpl.insertMemberByMobile(customer);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (customer.getRegisterType().equals("2")){
				try {
					systemServiceImpl.insertMemberByEmail(customer);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			log.error("验证码错误");
		}
		md.setViewName("login");
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
