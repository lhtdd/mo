package com.lyao.mo.business.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public ModelAndView doRegister(RegisterInfo customer){ 
		ModelAndView md = new ModelAndView();
		try {
			systemServiceImpl.insertMember(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.setViewName("login");
		return md;
	} 
	
}
